package com.yuroyoro.scala.spinupcheck

import java.util.logging.Logger

import javax.servlet.{Filter,FilterChain, FilterConfig }
import javax.servlet.{ServletRequest, ServletResponse}

class IndexController extends Filter {
  override def destroy = {}

  override def doFilter(req:ServletRequest, res:ServletResponse, filter:FilterChain) = {
    res.setContentType("text/plain")
    res.getWriter.println("Hello, world")
  }

  override def init(config:FilterConfig) =
    Logger.getLogger(classOf[IndexController].getName).info("Initialized IndexController")
}
