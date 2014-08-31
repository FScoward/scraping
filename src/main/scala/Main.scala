/**
 * Created by FScoward on 2014/08/22.
 */

import edu.uci.ics.crawler4j.crawler.{CrawlController, CrawlConfig}
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.robotstxt.{RobotstxtServer, RobotstxtConfig}

object Main {
  def main(args: Array[String]): Unit = {
    
//    println(extractText("http://ameblo.jp/takagakiayahi-blog/entry-11908940418.html"))
    
    val crawlStorageFolder = """C:\Users\Fscoward\Dropbox\scraping\storage"""
    val config = new CrawlConfig
    config.setCrawlStorageFolder(crawlStorageFolder)
    config.setPolitenessDelay(10000)
    
    val pageFetcher = new PageFetcher(config)
    val robotstxtConfig = new RobotstxtConfig
    val robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher)
    val controller = new CrawlController(config, pageFetcher, robotstxtServer)
    
    controller.addSeed("http://ameblo.jp/takagakiayahi-blog/")
    controller.start(classOf[Crawler], 1)
    
  }
  


}
