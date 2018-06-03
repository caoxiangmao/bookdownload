package book.sites;


import book.sites.biquge.BiQuGe;
import book.sites.biquge8.BiQuGe8;
import book.sites.biqugex.BiQuGeX;
import book.sites.biquguan.BiQuGuan;
import book.sites.txtjia.TxtJia;
import book.sites.xsla.XsLa;
import book.sites.xxbiquge.XxBiQuGe;
import book.sites.zw81.Zw81;

import java.util.ArrayList;
import java.util.List;

public class SiteList {

    List<Site> sites = new ArrayList<Site>();

    private static SiteList instance = new SiteList();

    private SiteList() {
        sites.add(new BiQuGe());
        sites.add(new BiQuGe8());
        sites.add(new BiQuGeX());
        sites.add(new BiQuGuan());
        sites.add(new XsLa());
        sites.add(new XxBiQuGe());
        sites.add(new TxtJia());
        sites.add(new Zw81());
    }

    public Site getSite(String bookUrl) {
        for (Site site : sites) {
            if (site.checkOwner(bookUrl)) {
                return site;
            }
        }
        return null;
    }

    public static SiteList getInstance() {
        return instance;
    }
}
