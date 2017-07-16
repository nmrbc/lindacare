package pt.ncastro.markettradeprocessor.util.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Some utilities to handle JSON.
 * 
 * @author Nuno de Castro
 *
 */
public class JSONUtils {

	/**
	 * Gets the JSON string from the request.
	 * 
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public static String getJSONContent(HttpServletRequest req) throws IOException {
		if (req != null) {
			InputStream is = null;
			ByteArrayOutputStream baos = null;
			try {
				// validate if we have a JSON request
				if (!"application/json".equals(req.getContentType())) {
					throw new RuntimeException("Requested content is not JSON");
				}

				// get the data
				is = req.getInputStream();
				baos = new ByteArrayOutputStream();
				byte[] buff = new byte[1024];
				int n;
				while ((n = is.read(buff)) > 0) {
					baos.write(buff, 0, n);
				}
				return baos.toString();

			} catch (Exception e) {
				throw new RuntimeException(e);

			} finally {
				// perform cleanups
				if (is != null) {
					is.close();
				}
				if (baos != null) {
					baos.close();
				}
			}
		}
		return null;
	}


	/**
	 * Gets the JSON object represented in the given request.
	 * 
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject getJSONObject(HttpServletRequest req) throws IOException, JSONException {
		return new JSONObject(getJSONContent(req));
	}


	/**
	 * Sends a JSON string as a response to a request.
	 * 
	 * @param resp
	 * @param content
	 * @throws IOException
	 */
	public static void sendJSONContent(HttpServletResponse resp, String content) throws IOException {
		if (resp != null) {
			byte[] data = content.getBytes();
			resp.setContentType("application/json");
			resp.setContentLength(data.length);

			OutputStream out = null;
			try {
				out = resp.getOutputStream();
				out.write(data);
				out.flush();

			} catch (Exception e) {
				throw new RuntimeException(e);

			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
	}


	/**
	 * Sends a JSON object as a response to a request.
	 * 
	 * @param resp
	 * @param object
	 * @throws IOException
	 */
	public static void sendJSONObject(HttpServletResponse resp, JSONObject object) throws IOException {
		sendJSONContent(resp, object.toString());
	}
}
