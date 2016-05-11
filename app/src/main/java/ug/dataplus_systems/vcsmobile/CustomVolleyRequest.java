package ug.dataplus_systems.vcsmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class CustomVolleyRequest
{
    private static Context context;
    private static CustomVolleyRequest customVolleyRequest;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private CustomVolleyRequest(Context paramContext)
    {
        context = paramContext;
        this.requestQueue = getRequestQueue();
        this.imageLoader = new ImageLoader(this.requestQueue, new ImageLoader.ImageCache()
        {
            private final LruCache<String, Bitmap> cache = new LruCache(20);

            public Bitmap getBitmap(String paramAnonymousString)
            {
                return (Bitmap)this.cache.get(paramAnonymousString);
            }

            public void putBitmap(String paramAnonymousString, Bitmap paramAnonymousBitmap)
            {
                this.cache.put(paramAnonymousString, paramAnonymousBitmap);
            }
        });
    }

    public static CustomVolleyRequest getInstance(Context paramContext)
    {
        try
        {
            if (customVolleyRequest == null) {
                customVolleyRequest = new CustomVolleyRequest(paramContext);
            }
         //   paramContext = customVolleyRequest;
            return customVolleyRequest;
        }
        finally {}
    }

    public ImageLoader getImageLoader()
    {
        return this.imageLoader;
    }

    public RequestQueue getRequestQueue()
    {
        if (this.requestQueue == null)
        {
            this.requestQueue = new RequestQueue(new DiskBasedCache(context.getCacheDir(), 10485760), new BasicNetwork(new HurlStack()));
            this.requestQueue.start();
        }
        return this.requestQueue;
    }
}
