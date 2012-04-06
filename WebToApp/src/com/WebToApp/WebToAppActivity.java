package com.WebToApp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebToAppActivity extends Activity {
    /** Called when the activity is first created. */
	
	
/*If a link is inside web site open it inside WebView, if it is outside open it with default browser*/   

private class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals("www.viverevasto.com")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        return true;
    }
    

}



/*Use back button to go back inside history*/

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	 
	WebView myWebView = (WebView) findViewById(R.id.webView1); 
	 
    // Check if the key event was the Back button and if there's history
    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
        myWebView.goBack();
        return true;
    }
    // If it wasn't the Back key or there's no web page history, bubble up to the default
    // system behavior (probably exit the activity)
    return super.onKeyDown(keyCode, event);
}





/*Start activity*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        WebView myWebView = (WebView) findViewById(R.id.webView1); 
        
        myWebView.loadUrl("http://www.viverevasto.com"); //Open URL
        myWebView.setWebViewClient(new MyWebViewClient()); //Set the WebViewClient that receive informations to open a page inside web view or outside
        WebSettings webSettings = myWebView.getSettings(); //Enable WebView settings
        webSettings.setJavaScriptEnabled(true); //Enable java script
        
    }
}