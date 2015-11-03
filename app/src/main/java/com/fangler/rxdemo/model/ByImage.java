package com.fangler.rxdemo.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangler on 2015/10/22.
 */
public class ByImage {

  /**
   * images : [{"startdate":"20151021","fullstartdate":"201510211600","enddate":"20151022","url":"http://s.cn.bing.net/az/hprichbg/rb/LofotenVideo_ZH-CN13780841105_1920x1080.jpg","urlbase":"/az/hprichbg/rb/LofotenVideo_ZH-CN13780841105","copyright":"挪威，罗弗敦群岛，斯塔戈萨登海滩 (© imageBROKER/REX Shutterstock)","copyrightlink":"http://www.bing.com/search?q=%E7%BD%97%E5%BC%97%E6%95%A6%E7%BE%A4%E5%B2%9B&form=hpcapt&mkt=zh-cn","wp":true,"hsh":"8df8df959fa23577467da6a209d41dc9","drk":1,"top":1,"bot":1,"hs":[{"desc":"上古的冰川雕刻出这片岛屿，","link":"http://www.bing.com/images/search?q=%E7%BD%97%E5%BC%97%E6%95%A6&form=hphot1&mkt=zh-cn","query":"像小船一样漂流在辽阔的海面上。","locx":20,"locy":49},{"desc":"海面升起了晨曦，那一定不是冰封的海洋，也不是跳动的火光，","link":"http://www.bing.com/videos/search?q=dancing+lights+Lofoten,+Norway&form=hphot2&mkt=zh-cn","query":"轻盈飘荡，飞舞的极光。","locx":38,"locy":33},{"desc":"亘古不化的冰雪无疑是艺术家最好的灵感之药，","link":"http://www.bing.com/images/search?q=Gunnar+Berg+paintings&form=hphot3&mkt=zh-cn","query":"冰川和暖阳，温润的颜色透过幅面，直指心间。","locx":64,"locy":48}],"msg":[{"title":"罗弗敦群岛","link":"http://www.bing.com/search?q=%E7%BD%97%E5%BC%97%E6%95%A6%E7%BE%A4%E5%B2%9B&form=pgbar1&mkt=zh-cn","text":"罗弗敦群岛"}]}]
   * tooltips : {"loading":"正在加载...","previous":"上一页","next":"下一页","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}
   */

  public TooltipsEntity tooltips;
  public List<ImagesEntity> images;

  public static ByImage objectFromData(String str) {

    return new Gson().fromJson(str, ByImage.class);
  }

  public static ByImage objectFromData(String str, String key) {

    try {
      JSONObject jsonObject = new JSONObject(str);

      return new Gson().fromJson(jsonObject.getString(str), ByImage.class);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static List<ByImage> arrayByImageFromData(String str) {

    Type listType = new TypeToken<ArrayList<ByImage>>() {
    }.getType();

    return new Gson().fromJson(str, listType);
  }

  public static List<ByImage> arrayByImageFromData(String str, String key) {

    try {
      JSONObject jsonObject = new JSONObject(str);
      Type listType = new TypeToken<ArrayList<ByImage>>() {
      }.getType();

      return new Gson().fromJson(jsonObject.getString(str), listType);

    } catch (JSONException e) {
      e.printStackTrace();
    }

    return new ArrayList();


  }

  public static class TooltipsEntity {
    /**
     * loading : 正在加载...
     * previous : 上一页
     * next : 下一页
     * walle : 此图片不能下载用作壁纸。
     * walls : 下载今日美图。仅限用作桌面壁纸。
     */

    public String loading;
    public String previous;
    public String next;
    public String walle;
    public String walls;

    public static TooltipsEntity objectFromData(String str) {

      return new Gson().fromJson(str, TooltipsEntity.class);
    }

    public static TooltipsEntity objectFromData(String str, String key) {

      try {
        JSONObject jsonObject = new JSONObject(str);

        return new Gson().fromJson(jsonObject.getString(str), TooltipsEntity.class);
      } catch (JSONException e) {
        e.printStackTrace();
      }

      return null;
    }

    public static List<TooltipsEntity> arrayTooltipsEntityFromData(String str) {

      Type listType = new TypeToken<ArrayList<TooltipsEntity>>() {
      }.getType();

      return new Gson().fromJson(str, listType);
    }

    public static List<TooltipsEntity> arrayTooltipsEntityFromData(String str, String key) {

      try {
        JSONObject jsonObject = new JSONObject(str);
        Type listType = new TypeToken<ArrayList<TooltipsEntity>>() {
        }.getType();

        return new Gson().fromJson(jsonObject.getString(str), listType);

      } catch (JSONException e) {
        e.printStackTrace();
      }

      return new ArrayList();


    }
  }

  public static class ImagesEntity {
    /**
     * startdate : 20151021
     * fullstartdate : 201510211600
     * enddate : 20151022
     * url : http://s.cn.bing.net/az/hprichbg/rb/LofotenVideo_ZH-CN13780841105_1920x1080.jpg
     * urlbase : /az/hprichbg/rb/LofotenVideo_ZH-CN13780841105
     * copyright : 挪威，罗弗敦群岛，斯塔戈萨登海滩 (© imageBROKER/REX Shutterstock)
     * copyrightlink : http://www.bing.com/search?q=%E7%BD%97%E5%BC%97%E6%95%A6%E7%BE%A4%E5%B2%9B&form=hpcapt&mkt=zh-cn
     * wp : true
     * hsh : 8df8df959fa23577467da6a209d41dc9
     * drk : 1
     * top : 1
     * bot : 1
     * hs : [{"desc":"上古的冰川雕刻出这片岛屿，","link":"http://www.bing.com/images/search?q=%E7%BD%97%E5%BC%97%E6%95%A6&form=hphot1&mkt=zh-cn","query":"像小船一样漂流在辽阔的海面上。","locx":20,"locy":49},{"desc":"海面升起了晨曦，那一定不是冰封的海洋，也不是跳动的火光，","link":"http://www.bing.com/videos/search?q=dancing+lights+Lofoten,+Norway&form=hphot2&mkt=zh-cn","query":"轻盈飘荡，飞舞的极光。","locx":38,"locy":33},{"desc":"亘古不化的冰雪无疑是艺术家最好的灵感之药，","link":"http://www.bing.com/images/search?q=Gunnar+Berg+paintings&form=hphot3&mkt=zh-cn","query":"冰川和暖阳，温润的颜色透过幅面，直指心间。","locx":64,"locy":48}]
     * msg : [{"title":"罗弗敦群岛","link":"http://www.bing.com/search?q=%E7%BD%97%E5%BC%97%E6%95%A6%E7%BE%A4%E5%B2%9B&form=pgbar1&mkt=zh-cn","text":"罗弗敦群岛"}]
     */

    public String startdate;
    public String fullstartdate;
    public String enddate;
    public String url;
    public String urlbase;
    public String copyright;
    public String copyrightlink;
    public boolean wp;
    public String hsh;
    public int drk;
    public int top;
    public int bot;
    public List<HsEntity> hs;
    public List<MsgEntity> msg;

    public static ImagesEntity objectFromData(String str) {

      return new Gson().fromJson(str, ImagesEntity.class);
    }

    public static ImagesEntity objectFromData(String str, String key) {

      try {
        JSONObject jsonObject = new JSONObject(str);

        return new Gson().fromJson(jsonObject.getString(str), ImagesEntity.class);
      } catch (JSONException e) {
        e.printStackTrace();
      }

      return null;
    }

    public static List<ImagesEntity> arrayImagesEntityFromData(String str) {

      Type listType = new TypeToken<ArrayList<ImagesEntity>>() {
      }.getType();

      return new Gson().fromJson(str, listType);
    }

    public static List<ImagesEntity> arrayImagesEntityFromData(String str, String key) {

      try {
        JSONObject jsonObject = new JSONObject(str);
        Type listType = new TypeToken<ArrayList<ImagesEntity>>() {
        }.getType();

        return new Gson().fromJson(jsonObject.getString(str), listType);

      } catch (JSONException e) {
        e.printStackTrace();
      }

      return new ArrayList();


    }

    public static class HsEntity {
      /**
       * desc : 上古的冰川雕刻出这片岛屿，
       * link : http://www.bing.com/images/search?q=%E7%BD%97%E5%BC%97%E6%95%A6&form=hphot1&mkt=zh-cn
       * query : 像小船一样漂流在辽阔的海面上。
       * locx : 20
       * locy : 49
       */

      public String desc;
      public String link;
      public String query;
      public int locx;
      public int locy;

      public static HsEntity objectFromData(String str) {

        return new Gson().fromJson(str, HsEntity.class);
      }

      public static HsEntity objectFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);

          return new Gson().fromJson(jsonObject.getString(str), HsEntity.class);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        return null;
      }

      public static List<HsEntity> arrayHsEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<HsEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
      }

      public static List<HsEntity> arrayHsEntityFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);
          Type listType = new TypeToken<ArrayList<HsEntity>>() {
          }.getType();

          return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
          e.printStackTrace();
        }

        return new ArrayList();


      }
    }

    public static class MsgEntity {
      /**
       * title : 罗弗敦群岛
       * link : http://www.bing.com/search?q=%E7%BD%97%E5%BC%97%E6%95%A6%E7%BE%A4%E5%B2%9B&form=pgbar1&mkt=zh-cn
       * text : 罗弗敦群岛
       */

      public String title;
      public String link;
      public String text;

      public static MsgEntity objectFromData(String str) {

        return new Gson().fromJson(str, MsgEntity.class);
      }

      public static MsgEntity objectFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);

          return new Gson().fromJson(jsonObject.getString(str), MsgEntity.class);
        } catch (JSONException e) {
          e.printStackTrace();
        }

        return null;
      }

      public static List<MsgEntity> arrayMsgEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<MsgEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
      }

      public static List<MsgEntity> arrayMsgEntityFromData(String str, String key) {

        try {
          JSONObject jsonObject = new JSONObject(str);
          Type listType = new TypeToken<ArrayList<MsgEntity>>() {
          }.getType();

          return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
          e.printStackTrace();
        }

        return new ArrayList();
      }
    }
  }
}
