package com.contentful.hello.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.contentful.java.cda.CDASpace;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Main class of a simple example of an Android App using Contentful.
 * <p>
 * This sample code shows you how to do the bare minimum of things needed to connect your Android
 * app to Contentful.
 * It'll create a client to communicate with Contentful, fetches information about the space it is
 * connected to, and shows how to retrieve all entries of this space and lastly how to filter
 * search your space for specific criteria.
 * </p>
 * <h1>Compilation</h1>
 * {@code ./gradlew build}
 * <h1>Running</h1>
 * {@code ./gradlew run}
 *
 * @see <a href="file:///app/src/main/AndroidManifest">AndroidManifest</a> for permissions needed.
 * @see <a href="file:///app/build.gradle">build.gradle</a> for dependencies.
 * @see <a href="http://contentful.com/">Contentful Main Website</a>
 * @see <a href="http://app.contentful.com/">Contentful Management WebApp</a>
 * @see <a href="http://doc.contentful.com/java/hello-world">Contentful documentation</a>
 */
public class MainActivity extends AppCompatActivity {

  /**
   * Token to communicate with the <b>c</b>ontent <b>d</b>elivery <b>a</b>pi.
   * Please use the WebApp to find your token, if it is not present here.
   *
   * @see <a href="http://app.contentful.com">WebApp</a>
   */
  private static final String CDA_TOKEN = "367c97f774b54c872a43979f6510e2675e809d40ddeec8a216202ef7ed0f09ec";

  /**
   * Space <b>id</b>entifier.
   * <p>
   * This identifier is used to know which space you want to request items from. Please also use the
   * WebApp, if this value is not set.
   *
   * @see <a href="http://app.contentful.com">WebApp</a>
   */
  private static final String SPACE_ID = "arqlnkt58eul";

  /*
   * Create the client
   *
   * This client will abstract the communication to Contentful. Use it to make your requests to
   * Contentful.
   *
   * For initialization it needs the {@link #CDA_TOKEN} and {@link #SPACE_ID} from above.
   */
  private final CDAClient client = CDAClient
      .builder()
      .setToken(CDA_TOKEN)
      .setSpace(SPACE_ID)
      .build();

  /**
   * Creates this activity.
   * <p>
   * Since the activity is not shown yet, we ignore this method.
   *
   * @param savedInstanceState arguments saved from the last time this activity opened, not used here.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  /**
   * Resume state of activity.
   * <p>
   * This method gets called once the activity should be active, or displayed, so it is the perfect
   * time for us to start talking to contentful.
   */
  @Override protected void onResume() {
    /*
     * After you created your client, let us retrieve the space we'll be using in this sample. The
     * following request will be executed asynchronously, since Android does not allow networking
     * on the main ui thread.
     */
    client
        .observeSpace()
        .observeOn(AndroidSchedulers.mainThread()) // run code in the following action on main thread
        .subscribeOn(Schedulers.io()) // run all other code (fetching, internet, etc) on different thread
        .subscribe(new Action1<CDASpace>() {
          @Override public void call(CDASpace space) {
            /*
             * Now that we have a space, we can find out the name of it. Thankfully the Contentful SDK has
             * already created an object based on the response from the Contentful API: A {@see CDASpace}.
             * This object does not contain all the entries of this space, it just allows us to retrieve the
             * general information of the space. Let us print the name we set in the WebApp of this space to
             * the command line.
             */
            Toast.makeText(MainActivity.this, "Fetched space with name: " + space.name(), Toast.LENGTH_SHORT).show();
          }
        });

    /*
     * Since we are actually more interested in the contents of the space, let us fetch all entries
     * of this space. The following statement will fetch all {@code Entries} of the space. We could
     * also fetch all {@see CDAAssets} and all {@see CDAContentTypes} of this space, by simply
     * exchanging the given CDAEntry class with the wanted one.
     */
    client
        .observe(CDAEntry.class)
        .all()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Action1<CDAArray>() {
          @Override public void call(CDAArray entries) {
            /*
             * The following snipped will toast out the id and type of all entries requested.
             */
            StringBuilder builder = new StringBuilder();
            for (final CDAResource resource : entries.items()) {
              // We are sure that all resources returned are a CDAEntry, since we specified it in the fetch
              // so we can directly cast it to an entry.
              final CDAEntry entry = (CDAEntry) resource;
              builder
                  .append(entry.id())
                  .append(": ")
                  .append(entry.contentType())
                  .append("\n");
            }

            Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();

            /*
             * The last thing we want to show with this app, is how to filter all of entries in a space, so
             * that we only return entries of a given type. Therefore we'll just get the type of the first
             * entry we requested above, and ask Contentful to return us all entries of this type,
             * printing the first field of it.
             */
            final String contentTypeId = (String) ((CDAEntry) entries.items().get(0)).contentType()
                .attrs()
                .get("id");

            client
                .observe(CDAEntry.class)
                .where("content_type", contentTypeId)
                .all()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<CDAArray>() {
                  @Override public void call(CDAArray entries) {
                    for (final CDAResource resource : entries.items()) {
                      final CDAEntry entry = (CDAEntry) resource;
                      final String description = String.format("%s of %s", entry.id(), entry.getAttribute("contentType"));
                      Toast.makeText(MainActivity.this, description, Toast.LENGTH_SHORT).show();
                    }
                  }
                });
          }
        });

    /*
     * And we are done. Please feel free to stick around, change some code to see how it works, or
     * continue reading more in depth guides at {@see <a href="docs.contentful.com/java"> our
     * documentation</a>}
     */
    super.onResume();
  }
}
