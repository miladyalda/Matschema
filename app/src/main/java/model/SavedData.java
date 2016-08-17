package model;

import android.util.SparseArray;

import com.matschema.my.matschema.MainActivity;

import java.util.Vector;

/**
 * Created by my on 2015-09-04.
 */
class SavedData
{

    public Vector _columns;
    public Vector _units;
    public String data[];
    public String elems[];
    public float fett[];
    public Filter filter;
    public String items[];
    public int ix0;
    public int ix1;
    public int ix2;
    public int ix3;
    public int ix4;
    public float karb[];
    public int numitems;
    public float prot[];
    public SparseArray resultset;
    public String searchresult;
    public String searchstring;


    SavedData()
    {

       // _columns = null;
        _units = null;
        filter = null;
    }
}