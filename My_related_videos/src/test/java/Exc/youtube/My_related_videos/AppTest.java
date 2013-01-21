package Exc.youtube.My_related_videos;

import junit.framework.TestCase;

/**
 * Unit test for simple Application.
 */
public class AppTest 
    extends TestCase
{
     /**
     * Rigourous Test :-)
     */
    public void testRegexp_url_full()
    {
        String url_in = "http://www.youtube.com/watch?v=s3foIwovkRI";
        String url_out = "http://www.youtube.com/watch?v=s3foIwovkRI";
        Application test = new Application();
        assertEquals(test.checkURL(url_in), url_out);
    	   }
    
	public void testRegexp_url_without_http()
    {
        String url_in = "www.youtube.com/watch?v=s3foIwovkRI";
        String url_out = "";
        Application test = new Application();
        assertEquals(test.checkURL(url_in), url_out);
    	   }
    public void testRegexp_url_without_www()
    {
        String url_in = "http://youtube.com/watch?v=s3foIwovkRI";
        String url_out = "";
        Application test = new Application();
        assertEquals(test.checkURL(url_in), url_out);
    	   }
    public void testRegexp_url_without_youtube()
    {
        String url_in = "http://www.tube.com/watch?v=s3foIwovkRI";
        String url_out = "";
        Application test = new Application();
        assertEquals(test.checkURL(url_in), url_out);
    	   }
    public void testRegexp_url_long()
    {
        String url_in = "http://www.youtube.com/watch?v=s3foIwovkRIihzzzz";
        String url_out = "http://www.youtube.com/watch?v=s3foIwovkRIihzzzz";
        Application test = new Application();
        assertEquals(test.checkURL(url_in), url_out);
    	   }
    
}
