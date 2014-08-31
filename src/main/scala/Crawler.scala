
/**
 * Created by FScoward on 2014/08/22.
 */

import java.io.{PrintWriter, File}

import edu.uci.ics.crawler4j.crawler.{Page, WebCrawler}
import edu.uci.ics.crawler4j.url.WebURL
import org.jsoup.Jsoup

class Crawler extends WebCrawler {
  
  /**
   * どのURLをクロールするか
   * */
  override def shouldVisit(url: WebURL) = {
    val href = url.getURL.toLowerCase
    (href.startsWith("http://ameblo.jp/takagakiayahi-blog/entry")
      && href.endsWith(".html"))
  }
  
  /**
   * 各処理
   * */
  override def visit(page: Page) = {
    val url = page.getWebURL.getURL
    println("URL: " + url)
    val (title, text) = extract(url)
    write(title, text)
  }
  
  def write(title: String, text: String) = {
    val writer = new PrintWriter(new File(title + ".txt"))
    writer.write(text)
    writer.close()
  }
  
  def extract(url: String) = {
    val source = Jsoup.connect(url).get
    val title = source.title.split("｜")(0)
    val text = source.select(".subContentsInner").text
    (title, text)
  }
}
