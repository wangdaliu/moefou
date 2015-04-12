package com.moefou.android.core;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.moefou.android.Application;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.object.wiki.WikiCover;
import com.moefou.android.object.wiki.WikiMeta;
import com.moefou.android.object.wiki.WikiUserFav;
import com.moefou.android.provider.MoeProvider;
import com.moefou.android.provider.MoeTables.TWiki;
import com.moefou.android.provider.MoeTables.TWikiCover;
import com.moefou.android.provider.MoeTables.TWikiMeta;
import com.moefou.android.provider.MoeTables.TWikiUserFav;

import java.util.ArrayList;
import java.util.List;

public class WikiManager {

    private static WikiManager mWikiManager = new WikiManager();

    private ContentResolver resolver = Application.getInstance().getContentResolver();

    public static WikiManager getInstance() {
        return mWikiManager;
    }

    public void saveWikiList(List<Wiki> wikis) {
        SQLiteDatabase db = null;
        try {
            db = MoeProvider.getSQLiteOpenHelper().getWritableDatabase();
            db.beginTransaction();
            for (Wiki wiki : wikis) {
                saveWiki(wiki);
            }
        } catch (Exception e) {

        } finally {
            if (null != db) {
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }
    }

    public void saveWiki(Wiki wiki) {
        Uri uri = resolver.insert(TWiki.CONTENT_URI, wiki.toContentValues());
        long wikiId = Long.parseLong(uri.getLastPathSegment());
        // save WikiCover
        WikiCover wikiCover = wiki.getWiki_cover();
        if (null != wikiCover) {
            wikiCover.setFkWikiId(wikiId);
            resolver.insert(TWikiCover.CONTENT_URI, wikiCover.toContentValues());
        }
        // save WikiMeta
        List<WikiMeta> wikiMetaList = wiki.getWiki_meta();
        if (null != wikiMetaList) {
            for (WikiMeta wikiMeta : wikiMetaList) {
                resolver.insert(TWikiMeta.CONTENT_URI, wikiMeta.toContentValues());
            }
        }
        // save WikiUserFav
        WikiUserFav wikiUserFav = wiki.getWiki_user_fav();
        if (null != wikiUserFav) {
            wikiUserFav.setFkWikiId(wikiId);
            resolver.insert(TWikiUserFav.CONTENT_URI, wikiUserFav.toContentValues());
        }
    }

    public List<Wiki> getWikisByType(String type) {
        Cursor wikiCursor = null;
        List<Wiki> wikis = new ArrayList<Wiki>();
        String where = "wiki_type='" + type + "'";
        try {
            wikiCursor = resolver.query(TWiki.CONTENT_URI, null, where, null, null);
            for (wikiCursor.moveToFirst(); !wikiCursor.isAfterLast(); wikiCursor.moveToNext()) {
                Wiki wiki = new Wiki(wikiCursor);
                wiki.setWiki_cover(getWikiCoverByWikiId(wiki.getId()));
                wiki.setWiki_user_fav(getWikiUserFavByWikiId(wiki.getId()));
                wiki.setWiki_meta(getWikiMetaByWikiId(wiki.getId()));
                wikis.add(wiki);
            }
        } catch (Exception e) {

        } finally {
            if (null != wikiCursor) {
                wikiCursor.close();
            }
        }
        return wikis;
    }

    private WikiCover getWikiCoverByWikiId(long wikiId) {
        Cursor cursor = null;
        WikiCover wikiCover = null;
        String where = "fk_wiki=" + wikiId;
        try {
            cursor = resolver.query(TWikiCover.CONTENT_URI, null, where, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                wikiCover = new WikiCover(cursor);
            }
        } catch (Exception e) {

        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return wikiCover;
    }

    private WikiUserFav getWikiUserFavByWikiId(long wikiId) {
        Cursor cursor = null;
        WikiUserFav wikiUserFav = null;
        String where = "fk_wiki=" + wikiId;
        try {
            cursor = resolver.query(TWikiUserFav.CONTENT_URI, null, where, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                wikiUserFav = new WikiUserFav(cursor);
            }
        } catch (Exception e) {

        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return wikiUserFav;
    }

    private List<WikiMeta> getWikiMetaByWikiId(long wikiId) {
        Cursor cursor = null;
        List<WikiMeta> wikiMetas = new ArrayList<WikiMeta>();
        String where = "fk_wiki=" + wikiId;
        try {
            cursor = resolver.query(TWikiMeta.CONTENT_URI, null, where, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                wikiMetas.add(new WikiMeta(cursor));
            }
        } catch (Exception e) {

        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return wikiMetas;
    }

    public void removeWiki(String type) {
        String where = "wiki_type='" + type + "'";
        resolver.delete(TWikiUserFav.CONTENT_URI, where, null);
    }
}
