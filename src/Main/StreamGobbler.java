package Main;

import java.io.*;

public class StreamGobbler
        extends Thread
{
    StreamGobbler( InputStream is )
    {
        this(is, System.out) ;
    }

    private StreamGobbler( InputStream is, PrintStream ps )
    {
        this.is= is ;
        this.ps= ps ;
    }
    private final InputStream is ;
    private final PrintStream ps ;

    @Override
    public void run()
    {
        try {
            InputStreamReader isr= new InputStreamReader(is) ;
            BufferedReader br= new BufferedReader(isr) ;
            for ( String line ; (line= br.readLine()) != null ; ) {
                ps.println(line) ;
            }
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace() ;
        }
    }
}