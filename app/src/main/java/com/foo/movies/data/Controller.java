package com.foo.movies.data;


import com.foo.movies.data.db.IDBHelper;
import com.foo.movies.data.network.IApiHelper;

/**
 * Single entry point for all the operations including database and network calls.
 *
 * @author mohammed.rampurawala
 */

public interface Controller extends IApiHelper, IDBHelper {

}
