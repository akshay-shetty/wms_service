package nursing.emittoz.com.sample_wms_service.WMS;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by ShettyDev.
 */


public class TileProviderFactory {

	public static WMSTileProvider  getOsgeoWmsTileProvider(String STR_WMS_LAYER_NAME,String BASE_URL) {

        //This is configured for:
        // http://beta.sedac.ciesin.columbia.edu/maps/services
        // (TODO check that this WMS service still exists at the time you try to run this demo,
        // if it doesn't, find another one that supports EPSG:900913
		final String WMS_FORMAT_STRING =
                         BASE_URL +
						"?service=WMS" +
						"&version=1.1.1" +
						"&request=GetMap" +
						"&layers="+ STR_WMS_LAYER_NAME +
						"&bbox=%f,%f,%f,%f" +
						"&width=256" +
						"&height=256" +
						"&srs=EPSG:900913" +
						"&format=image/png" +
						"&transparent=true";


		WMSTileProvider tileProvider = new WMSTileProvider(256,256) {
        	
	        @Override
	        public synchronized URL getTileUrl(int x, int y, int zoom) {
	        	double[] bbox = getBoundingBox(x, y, zoom);
	            String s = String.format(Locale.US, WMS_FORMAT_STRING, bbox[MINX],
	            		bbox[MINY], bbox[MAXX], bbox[MAXY]);

	            Log.d("WMSDEMO", s);

	            URL url = null;
	            try {
	                url = new URL(s);

	            } catch (MalformedURLException e) {
	                throw new AssertionError(e);
	            }
	            return url;
	        }

		};



		return tileProvider;
	}

}
