object FileOut{
  import java.io.{Writer, OutputStreamWriter, FileOutputStream }

  def os(fileName:String):Writer =
    new OutputStreamWriter( new FileOutputStream( fileName ), "UTF-8")

  def apply( fileName:String, content:String ) = {
    val o = os( fileName )
    o.write( content )
    o.flush
    o.close
  }
  def apply( fileName:String, content:Seq[String] ) = {
    val o = os( fileName )
    content.foreach { c => o.write( c + "\n" ) }
    o.flush
    o.close
  }
}

object SpinUpLog{
  import scala.io.Source
  import scala.xml.XML
  def agregate( appname:String ) = {
      val r = "([0-9]+)cpu_ms".r
      val logs = Source.fromFile( appname + "/log.html").getLines.map{
        _.stripLineEnd }.map{
        case r(t) => t
        case _ => ""
      }.filter{ _ != ""}.map{ _.toLong }.toList

      val max = ( 0L /: logs){ ( n,m ) => if( n > m ) n else m }
      val min = ( 9999999L /: logs){ ( n,m ) => if( n < m ) n else m }
      val ave = ( 0L /: logs){ _ + _ } / logs.size

      val header = """

  %s
  ---------------------------------------
  max : %scpu_ms
  min : %scpu_ms
  ave : %scpu_ms
  ---------------------------------------

  """.format( appname,max,min,ave)

     header + logs.zipWithIndex.map{ case (t,i) =>
        "%s : %s".format( i,t) }.mkString("\n")
  }

  def main( args:String ) = {
    FileOut("log.txt",
      agregate("simplescala") + agregate("listscala") +
      agregate("slim3scala")  + agregate("slim3listscala"))
  }
}
