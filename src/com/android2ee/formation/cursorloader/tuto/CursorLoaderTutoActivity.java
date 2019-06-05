/**<ul>
 * <li>RessourcesTuto</li>
 * <li>com.android2ee.formation.cursorloader.tuto</li>
 * <li>20 déc. 2011</li>
 * 
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.formation.cursorloader.tuto;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class CursorLoaderTutoActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
	SimpleCursorAdapter mAdapter;
	ListView listV;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Create an empty adapter we will use to display the loaded data.
		mAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, null,
				new String[] { Contacts.DISPLAY_NAME, Contacts.CONTACT_STATUS },
				new int[] { android.R.id.text1, android.R.id.text2 }, 0);
		listV = (ListView) findViewById(R.id.list);
		listV.setAdapter(mAdapter);

		// Prepare the loader. Either re-connect with an existing one,
		// or start a new one.
		getLoaderManager().initLoader(0, null, this);
	}

	// Callbacks
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Uri baseUri = ContactsContract.Data.CONTENT_URI;
		return new CursorLoader(this, baseUri, null, null, null, null);
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		Log.e("ResourceActivity", "onLoadFinished called");
		mAdapter.swapCursor(data);
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
}