package book.sites;


import book.sites.biquge8.BiQuGe8;
import book.sites.biquguan.BiQuGuan;
import book.sites.txtjia.TxtJia;
import book.sites.xsla.XsLa;
import book.sites.xxbiquge.XxBiQuGe;

import java.util.ArrayList;
import java.util.List;

public class SiteList {

    List<Site> sites = new ArrayList<Site>();

    private static SiteList instance = new SiteList();

    private SiteList() {
        sites.add(new BiQuGe8());
        sites.add(new BiQuGuan());
        sites.add(new XsLa());
        sites.add(new XxBiQuGe());
        sites.add(new TxtJia());
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
