package com.ContentProvider.FMCG;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    static final String TAG = "SYNC_ADAPTER";
    static String ITEM_ID;
    static String DISC_PERC;
    static String DISC_VAL;
    static String TAX_PER;
    static String TAX_VALUE;
    static String TAX_PER1;
    static String TAX_VALUE1;
    static String PREFIX_ID;
    static String TAX_PER2;
    static String TAX_VALUE2;
    static String TAX_PER3;
    static String TAX_VALUE3;
    static String CUST_ID;
    static String CUST_NAME;
    static String CUST_CNCT;
    static String DOCT_NAME;
    static String DOCT_SPEC;
    static String CARD_TYPE;
    static String SAVETOTALBILLDISCOUNT;
    static String SAVETOTALBILLAMOUNT;
    static String SAVEFINALBILLAMOUNT;
    static String SAVERECEIVEDBILLAMOUNT;
    static String SAVEEXPECTEDBILLAMOUNT;
    static String TAX_ID;
    static String EXP_DATE;
    static String UOM;
    static String E_MAIL;
    static String TICKET_ID;
    static String BANK_NAME;
    static String ID;
    static String TRI_ID;
    static String GRAND_TOTAL;
    static String SALE_DATE;
    static String SALE_TIME;
    static String CARD_NO;
    static String TOTAL_POINTS;
    static String SCHEME_POINTS;
    static String CREATED_BY;
    static String CREATED_ON;
    static String EX_TRI_ID;
    static String ORDER_TYPE;
    static String PICK_UP_LOCATION;
    static String LINE_ITEM_DISC;
    static String LINE_DISC;
    static String DSTR_ID;
    static String DSTR_NM;
    static String DSTR_CNTCT_NM;
    static String CTR_NM;
    static String DSTR_INV;
    static String BARCODE;
    static String P_PRICE;
    static String TAX_DESC;
    static String PROD_HIER_DESC_3;
    static String PACK_UNIT_1;
    static String PACK_UNIT_2;
    static String MFG;
    static String SELLING_ORDER_UNIT;
    static String INTERNET_PRICE;
    static String IS_PROD_REL_INT;
    static String C_IMG;
    static String PROFIT_MARGIN;
    static String IND_NM;
    static String DISCOUNT_PERCENT;
    static String DISCOUNT_PERCENT_AMOUNT;
    static String FLAG;
    static String PO_NO;
    static String AD_MAIN_ID;
    static String PROD_ID;
    static String PROD_NM;
    static String PROD_SHORT_NM;
    static String USR_NM;
    static String FIRST_NAME;
    static String LAST_NAME;
    static String PASSWORD;
    static String CONFIRM_PASSWORD;
    static String ACTIVE;
    static String LAST_MODIFIED;
    static String REASON_FOR_REJECTION;
    static String SUBJECT_DESC;
    static String SUPPORT_PRIORITY;
    static String SUPPORT_TICKET_STATUS;
    static String TEAM_GROUP;
    static String TEAM_MEMBER;
    static String COMMENT;
    static String STATUS;
    static String USER;
    static String DATE;
    static String MRP_DECIMAL;
    static String P_PRICE_DECIMAL;
    static String S_PRICE_DECIMAL;
    static String HOLD_PO;
    static String HOLD_INV;
    static String HOLD_SALES;
    static String ROUND_OFF;
    static String SALES_HOLD_NO;
    static String PURCHASE_HOLD_NO;
    static String INV_HOLD_NO;
    static String BUSINESS_DATE;
    static String START_DATE;
    static String START_CASH;
    static String CBCASH_ONHAND;
    static String START_TIME;
    static String START_USER_ID;
    static String OPCASH_ONHAND;
    static String OP_CUR;
    static String END_DATE;
    static String END_TIME;
    static String END_USER_ID;
    static String CB_CUR;
    static String REMARKS;
    static String STORE_ID;
    static String STORE_NAME;
    static String STORE_ADDRESS;
    static String STORE_COUNTRY;
    static String TELE_1;
    static String TELE_2;
    static String MAIN_BODY;
    static String MRP;
    static String S_PRICE;
    static String QTY;
    static String TOTAL;
    static String TOTAL_BILL_VALUE;
    static String DISCOUNT;
    static String NET_BILL_PAYABLE;
    static String AMOUNT_RECEIVED;
    static String AMOUNT_PAID_BACK;
    static String FOOTER;
    static String S_FLAG;
    static String POS_USER;
    static String M_FLAG;
    static String NORMAL_SALES;
    static String CREDIT_CUSTOMER;
    static String RETURNS;
    static String SLAVE_FLAG;
    static String MARGIN;
    static String FREE_GOODS;
    static String INV_PRINT;
    static String NAME;
    static String MOBILE_NO;
    static String CREDIT_CUST;
    static String VEND_ID;
    static String VEND_NM;
    static String VEND_CNTCT_NM;
    static String ADD_1;
    static String CITY;
    static String CTR_DESC;
    static String ZIP;
    static String TELE;
    static String MOBILE;
    static String EMAIL;
    static String VEND_INV;
    static String CUST_ADD;
    static String DFCC;
    static String EX_PROD_ID;
    final ContentResolver resolver;
    HashMap<String, String> hashMaps = new HashMap<>();
    ArrayList<String> storeprodlocal = new ArrayList<>();
    ArrayList<String> salesdetail = new ArrayList<>();
    ArrayList<String> salesmaster = new ArrayList<>();
    ArrayList<String> strdstr = new ArrayList<>();
    ArrayList<String> storeprod = new ArrayList<>();
    ArrayList<String> topproduct = new ArrayList<>();
    ArrayList<String> employees = new ArrayList<>();
    ArrayList<String> vendreject = new ArrayList<>();
    ArrayList<String> custreject = new ArrayList<>();
    ArrayList<String> storemaint = new ArrayList<>();
    ArrayList<String> storedecimal = new ArrayList<>();
    ArrayList<String> dayopenclose = new ArrayList<>();
    ArrayList<String> billdisplay = new ArrayList<>();
    ArrayList<String> billvisible = new ArrayList<>();
    ArrayList<String> retailcust = new ArrayList<>();
    ArrayList<String> storeprodcom = new ArrayList<>();
    ArrayList<String> storevendor = new ArrayList<>();

    public SyncAdapter(Context c, boolean autoInit) {
        this(c, autoInit, false);
    }

    public SyncAdapter(Context c, boolean autoInit, boolean parallelSync) {
        super(c, autoInit, parallelSync);
        this.resolver = c.getContentResolver();
    }

    /**
     * Manual force Android to perform a sync with our SyncAdapter.
     */

    public static void performSync() {
        Bundle b = new Bundle();
        b.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        b.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(AccountGeneral.getAccount(),
                ArticleContract.CONTENT_AUTHORITY, b);
    }

    @Override

    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        Log.w(TAG, "Starting synchronization...");

        try {
            // Synchronize our news feed
            getStoreId();
            syncStrSalesDetail(syncResult);/////////////////////// all done
            syncStrSalesMaster(syncResult);/////////////////////// all done
            syncStrProdLocal(syncResult);///////////////////////// all done
            syncRetailProdCom(syncResult);///////////////////////////// all done
            syncstoreprodcpg(syncResult);///////////////////////// all done
            syncStrDistributor(syncResult);///////////////////////////// all done
            synctopproduct(syncResult);/////////////////////////////////// all done
            syncemployees(syncResult);/////////////////////////////// all done
            synccustreject(syncResult);/////////////////////////////////// all done
            syncvendreject(syncResult);/////////////////////////////////// all done
            syncStoreMaint(syncResult);/////////////////////////// all done
            syncRetailstrdecimal(syncResult);/////////////////////////// all done
            syncDayopenclose(syncResult);///////////////////////////////// all done
            syncRetailbilldisplay(syncResult);////////////////////////// all done
            syncRetailbillvisible(syncResult);////////////////////////// all done
            syncRetailStrVendor(syncResult);////////////////////////////// all done
            syncRetailcust(syncResult);////////////////////////////all done

        } catch (IOException ex) {
            Log.e(TAG, "Error synchronizing!", ex);
            syncResult.stats.numIoExceptions++;
        } catch (JSONException ex) {
            Log.e(TAG, "Error synchronizing!", ex);
            syncResult.stats.numParseExceptions++;
        } catch (RemoteException | OperationApplicationException ex) {
            Log.e(TAG, "Error synchronizing!", ex);
            syncResult.stats.numAuthExceptions++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.w(TAG, "Finished synchronization!");
    }

    public String getStoreId() {
        Cursor c = resolver.query(ArticleContract.RetailStore.CONTENT_URI_RETAIL_STORE, null, null, null, null, null);
        assert c != null;
        String STORE = null;
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            STORE = c.getString(c.getColumnIndex(ArticleContract.RetailStore.STORE_ID));
        }
        return STORE;
    }

    void syncStrProdLocal(SyncResult syncstrprodlocalResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_store_prod_local.jsp?STORE_ID=" + store_id;
        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_store_prod_local...");
        Map<String, StoreProdLocal> networkEntriesStrProdLocal = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonStrProdLocal = new JSONObject(jsonFeed);
        JSONArray jsonStrProdLocalArticles = jsonStrProdLocal.getJSONArray("RETAIL_STORE_PROD_LOCAL");
        for (int i = 0; i < jsonStrProdLocalArticles.length(); i++) {
            StoreProdLocal tempStrProdLocal = StoreProdLocalParser.parse(jsonStrProdLocalArticles.optJSONObject(i));
            networkEntriesStrProdLocal.put(tempStrProdLocal.getPROD_ID() + tempStrProdLocal.getPROD_NM(), tempStrProdLocal);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.StrProdLocal.CONTENT_URI_STR_PROD_LOCAL, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        StoreProdLocal foundtempStrProdLocal;
        JSONArray jsonArray = new JSONArray();
        Log.d("******", String.valueOf(c.getCount()));

        for (int i = 0; i < c.getCount(); i++) {
            syncstrprodlocalResult.stats.numEntries++;
            // Create local article entry

            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.STORE_ID));
            PROD_ID = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.PROD_ID));
            PROD_NM = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.PROD_NAME));
            BARCODE = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.BARCODE));
            MRP = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.MRP));
            S_PRICE = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.S_PRICE));
            P_PRICE = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.P_PRICE));
            TAX_ID = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.TAX_ID));
            INTERNET_PRICE = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.INTERNET_PRICE));
            IS_PROD_REL_INT = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.IS_PROD_REL_INT));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.ACTIVE));
            PROFIT_MARGIN = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.PROFIT_MARGIN));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.S_FLAG));
            IND_NM = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.IND_NM));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.M_FLAG));
            DISCOUNT_PERCENT = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.DISCOUNT_PERCENT));
            SELLING_ORDER_UNIT = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.SELLING_ORDER_UNIT));
            DISCOUNT_PERCENT_AMOUNT = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.DISCOUNT_PERCENT_AMOUNT));
            EX_PROD_ID = c.getString(c.getColumnIndex(ArticleContract.StrProdLocal.EX_PROD_ID));

            // Try to retrieve the local entry from network entries

            foundtempStrProdLocal = networkEntriesStrProdLocal.get(PROD_ID + PROD_NM);
            if (foundtempStrProdLocal != null && foundtempStrProdLocal.getACTIVE().equals(ACTIVE) && foundtempStrProdLocal.getP_PRICE().equals(P_PRICE) && foundtempStrProdLocal.getS_PRICE().equals(S_PRICE) && foundtempStrProdLocal.getDISCOUNT_PERCENT().equals(DISCOUNT_PERCENT) && foundtempStrProdLocal.getMRP().equals(MRP) && foundtempStrProdLocal.getSELLING_ORDER_UNIT().equals(SELLING_ORDER_UNIT) && foundtempStrProdLocal.getPOS_USER().equals(POS_USER)) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntriesStrProdLocal.remove(PROD_ID + PROD_NM);
            } else {

                storeprodlocal.add(STORE_ID);
                storeprodlocal.add(PROD_ID);
                storeprodlocal.add(PROD_NM);
                storeprodlocal.add(BARCODE);
                storeprodlocal.add(MRP);
                storeprodlocal.add(S_PRICE);
                storeprodlocal.add(P_PRICE);
                storeprodlocal.add(TAX_ID);
                storeprodlocal.add(INTERNET_PRICE);
                storeprodlocal.add(IS_PROD_REL_INT);
                storeprodlocal.add(ACTIVE);
                storeprodlocal.add(PROFIT_MARGIN);
                storeprodlocal.add(S_FLAG);
                storeprodlocal.add(IND_NM);
                storeprodlocal.add(POS_USER);
                storeprodlocal.add(M_FLAG);
                storeprodlocal.add(DISCOUNT_PERCENT);
                storeprodlocal.add(SELLING_ORDER_UNIT);
                storeprodlocal.add(DISCOUNT_PERCENT_AMOUNT);
                storeprodlocal.add(EX_PROD_ID);
                storeprodlocal.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempStrProdLocal = new StringBuilder(128);
        for (String value : storeprodlocal) {
            if (sbtempStrProdLocal.length() > 0) {
                sbtempStrProdLocal.append("|");
            }
            sbtempStrProdLocal.append(value);
        }
        sbtempStrProdLocal.insert(0, "|");
        HashMap<String, String> tempstrprodlocal = new HashMap<>();
        tempstrprodlocal.put("RETAIL_STORE_PROD_LOCAL", String.valueOf(sbtempStrProdLocal));
        Log.d("*retail_str_prod_local*", String.valueOf(sbtempStrProdLocal));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_store_prod_local_upload.jsp", tempstrprodlocal);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_store_prod_local");
            } else {
                Log.d("Resposnse", success + " " + "retail_store_prod_local");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void syncStrSalesDetail(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/RETAIL_STR_SALES_DETAIL.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Calendar cal = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        cal.setTime(now);
        String start = sdf.format(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, -3);
        String end = sdf.format(cal.getTimeInMillis());

        Log.e("Start date", start);
        Log.e("End date", end);

        Log.i(TAG, "Fetching server entries..." + end + " to " + start);
        Map<String, SalesDetail> networkEntries = new HashMap<>();
        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);
        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_STR_SALES_DETAIL");
        Log.d("Lenght", String.valueOf(jsonArticles.length()));
        for (int k = 0; k < jsonArticles.length(); k++) {
            SalesDetail salesDetail = SalesDetailParser.parse(jsonArticles.optJSONObject(k));
            networkEntries.put(salesDetail.getTRI_ID() + salesDetail.getPROD_NM(), salesDetail);
        }
        Log.d("&&&&& server", String.valueOf(networkEntries.size()));

        Set keys = networkEntries.keySet();
        System.out.println("All keys are: " + keys);

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        String[] params = new String[1];
        params[0] = start + "%";
        Cursor c = resolver.query(ArticleContract.SalesDetail.CONTENT_URI_SALES_DETAIL, null, "SALE_DATE between ? and ?", new String[]{end, start}, null);

        assert c != null;
        c.moveToFirst();
        Log.d("&&&&& local", String.valueOf(c.getCount()));

        SalesDetail found;

        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.STORE_ID));
            TRI_ID = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TRI_ID));
            BARCODE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.BARCODE));
            PROD_NM = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.PROD_NM));
            EXP_DATE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.EXP_DATE));
            S_PRICE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.S_PRICE));
            QTY = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.QTY));
            MRP = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.MRP));
            UOM = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.UOM));
            TOTAL = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TOTAL));
            CARD_NO = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.CARD_NO));
            ITEM_ID = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.ITEM_ID));
            DISC_PERC = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.DISC_PERC));
            DISC_VAL = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.DISC_VAL));
            TAX_PER = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_PER));
            TAX_VALUE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_VALUE));
            TAX_PER1 = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_PER1));
            TAX_VALUE1 = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_VALUE1));
            PREFIX_ID = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.PREFIX_ID));
            TAX_PER2 = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_PER2));
            TAX_VALUE2 = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_VALUE2));
            TAX_PER3 = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_PER3));
            TAX_VALUE3 = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.TAX_VALUE3));
            LAST_MODIFIED = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.LAST_MODIFIED));
            FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.FLAG));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.S_FLAG));
            SALE_DATE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.SALE_DATE));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.M_FLAG));
            EX_TRI_ID = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.EX_TRI_ID));
            ORDER_TYPE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.ORDER_TYPE));
            PICK_UP_LOCATION = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.PICK_UP_LOCATION));
            DISCOUNT_PERCENT = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.DISCOUNT_PERCENT));
            LINE_ITEM_DISC = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.LINE_ITEM_DISC));
            LINE_DISC = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.LINE_DISC));
            PROD_ID = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.PROD_ID));
            CARD_TYPE = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.CARD_TYPE));
            BANK_NAME = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.BANK_NAME));
            SLAVE_FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesDetail.SLAVE_FLAG));

            // Try to retrieve the local entry from network entries

            found = networkEntries.get(TRI_ID + PROD_NM);

            if (found != null) {
                networkEntries.remove(TRI_ID + PROD_NM);

            } else {
                salesdetail.add(STORE_ID);
                salesdetail.add(TRI_ID);
                salesdetail.add(BARCODE);
                salesdetail.add(PROD_NM);
                salesdetail.add(EXP_DATE);
                salesdetail.add(S_PRICE);
                salesdetail.add(QTY);
                salesdetail.add(MRP);
                salesdetail.add(UOM);
                salesdetail.add(TOTAL);
                salesdetail.add(CARD_NO);
                salesdetail.add(ITEM_ID);
                salesdetail.add(DISC_PERC);
                salesdetail.add(DISC_VAL);
                salesdetail.add(TAX_PER);
                salesdetail.add(TAX_VALUE);
                salesdetail.add(TAX_PER1);
                salesdetail.add(TAX_VALUE1);
                salesdetail.add(PREFIX_ID);
                salesdetail.add(TAX_PER2);
                salesdetail.add(TAX_VALUE2);
                salesdetail.add(TAX_PER3);
                salesdetail.add(TAX_VALUE3);
                salesdetail.add(FLAG);
                salesdetail.add(S_FLAG);
                salesdetail.add(SALE_DATE);
                salesdetail.add(POS_USER);
                salesdetail.add(M_FLAG);
                salesdetail.add(EX_TRI_ID);
                salesdetail.add(ORDER_TYPE);
                salesdetail.add(PICK_UP_LOCATION);
                salesdetail.add(DISCOUNT_PERCENT);
                salesdetail.add(LINE_ITEM_DISC);
                salesdetail.add(LINE_DISC);
                salesdetail.add(PROD_ID);
                salesdetail.add(BANK_NAME);
                salesdetail.add(CARD_TYPE);
                salesdetail.add(SLAVE_FLAG);
                salesdetail.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sb40 = new StringBuilder(128);
        for (String value : salesdetail) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> crms = new HashMap<>();
        crms.put("RETAIL_STR_SALES_DETAIL", String.valueOf(sb40));
        Log.d("*retail_str_sales_detl*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_str_sales_detail_upload.jsp", crms);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_str_sales_detail");
            } else {
                Log.d("Resposnse", success + " " + "retail_str_sales_detail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.SalesDetail.CONTENT_URI_SALES_DETAIL, // URI where data was modified
                null, false); // IMPORTANT: Do not sync to network
    }

    private void syncStrSalesMaster(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_str_sales_master.jsp?STORE_ID=" + store_id;
        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, SalesMaster> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);
        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_STR_SALES_MASTER");
        for (int i = 0; i < jsonArticles.length(); i++) {
            SalesMaster salesMaster = SalesMasterParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(salesMaster.getTRI_ID(), salesMaster);
        }

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();
        Log.d("&&&&& server", String.valueOf(networkEntries.size()));
        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Calendar cal = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        cal.setTime(now);
        String start = sdf.format(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, -3);
        String end = sdf.format(cal.getTimeInMillis());

        Log.e("Start date", start);
        Log.e("End date", end);

        Cursor c = resolver.query(ArticleContract.SalesMaster.CONTENT_URI_SALES_MASTER, null, "SALE_DATE between ? and ?", new String[]{end, start}, null);
        assert c != null;
        c.moveToFirst();
        Log.d("&&&&& local", String.valueOf(c.getCount()));

        SalesMaster found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry
            TRI_ID = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.TRI_ID));
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.STORE_ID));
            GRAND_TOTAL = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.GRAND_TOTAL));
            BUSINESS_DATE = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.BUSINESS_DATE));
            SALE_DATE = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SALE_DATE));
            SALE_TIME = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SALE_TIME));
            CARD_NO = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CARD_NO));
            LAST_MODIFIED = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.LAST_MODIFIED));
            TOTAL_POINTS = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.TOTAL_POINTS));
            SCHEME_POINTS = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SCHEME_POINTS));
            FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.FLAG));
            CREATED_BY = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CREATED_BY));
            CREATED_ON = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CREATED_ON));
            CUST_ID = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CUST_ID));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.M_FLAG));
            EX_TRI_ID = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.EX_TRI_ID));
            ORDER_TYPE = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.ORDER_TYPE));
            PICK_UP_LOCATION = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.PICK_UP_LOCATION));
            DISCOUNT_PERCENT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.DISCOUNT_PERCENT));
            LINE_ITEM_DISC = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.LINE_ITEM_DISC));
            LINE_DISC = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.LINE_DISC));
            CUST_NAME = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CUST_NAME));
            CUST_CNCT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CUST_CNCT));
            DOCT_NAME = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.DOCT_NAME));
            DOCT_SPEC = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.DOCT_SPEC));
            BANK_NAME = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.BANK_NAME));
            CARD_TYPE = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.CARD_TYPE));
            SAVETOTALBILLDISCOUNT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SAVETOTALBILLDISCOUNT));
            SAVETOTALBILLAMOUNT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SAVETOTALBILLAMOUNT));
            SAVEFINALBILLAMOUNT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SAVEFINALBILLAMOUNT));
            SAVERECEIVEDBILLAMOUNT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SAVERECEIVEDBILLAMOUNT));
            SAVEEXPECTEDBILLAMOUNT = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SAVEEXPECTEDBILLAMOUNT));
            SLAVE_FLAG = c.getString(c.getColumnIndex(ArticleContract.SalesMaster.SLAVE_FLAG));
            // Try to retrieve the local entry from network entries
            found = networkEntries.get(TRI_ID);

            if (found != null) {
                networkEntries.remove(TRI_ID);

            } else {

                salesmaster.add(TRI_ID);
                salesmaster.add(STORE_ID);
                salesmaster.add(GRAND_TOTAL);
                salesmaster.add(BUSINESS_DATE);
                salesmaster.add(SALE_DATE);
                salesmaster.add(SALE_TIME);
                salesmaster.add(CARD_NO);
                salesmaster.add(TOTAL_POINTS);
                salesmaster.add(SCHEME_POINTS);
                salesmaster.add(FLAG);
                salesmaster.add(CREATED_BY);
                salesmaster.add(CREATED_ON);
                salesmaster.add(CUST_ID);
                salesmaster.add(S_FLAG);
                salesmaster.add(POS_USER);
                salesmaster.add(M_FLAG);
                salesmaster.add(EX_TRI_ID);
                salesmaster.add(ORDER_TYPE);
                salesmaster.add(PICK_UP_LOCATION);
                salesmaster.add(DISCOUNT_PERCENT);
                salesmaster.add(LINE_ITEM_DISC);
                salesmaster.add(LINE_DISC);
                salesmaster.add(CUST_NAME);
                salesmaster.add(CUST_CNCT);
                salesmaster.add(DOCT_NAME);
                salesmaster.add(DOCT_SPEC);
                salesmaster.add(BANK_NAME);
                salesmaster.add(CARD_TYPE);
                salesmaster.add(SAVETOTALBILLDISCOUNT);
                salesmaster.add(SAVETOTALBILLAMOUNT);
                salesmaster.add(SAVEFINALBILLAMOUNT);
                salesmaster.add(SAVERECEIVEDBILLAMOUNT);
                salesmaster.add(SAVEEXPECTEDBILLAMOUNT);
                salesmaster.add(SLAVE_FLAG);
                salesmaster.add(";");
            }
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : salesmaster) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> salesmaster = new HashMap<>();
        salesmaster.put("RETAIL_STR_SALES_MASTER", String.valueOf(sb40));
        Log.d("*retail_str_sales_mstr*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_str_sales_master_upload.jsp", salesmaster);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_str_sales_master");
            } else {
                Log.d("Response", success + " " + "retail_str_sales_master");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.SalesMaster.CONTENT_URI_SALES_MASTER, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    void syncStrDistributor(SyncResult syncstrdistributorResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_str_dstr.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_str_dstr...");
        Map<String, StrDistributor> networkEntriesStrDistributor = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonStrDistributor = new JSONObject(jsonFeed);
        JSONArray jsonStrDistributorArticles = jsonStrDistributor.getJSONArray("RETAIL_STR_DSTR");
        for (int i = 0; i < jsonStrDistributorArticles.length(); i++) {
            StrDistributor strDistributor = StrDistributorParser.parse(jsonStrDistributorArticles.optJSONObject(i));
            networkEntriesStrDistributor.put(strDistributor.getSTORE_ID(), strDistributor);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.StrDistributor.CONTENT_URI_STR_DISTRIBUTOR, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        StrDistributor found;
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < c.getCount(); i++) {
            syncstrdistributorResult.stats.numEntries++;
            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.STORE_ID));
            DSTR_ID = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.DSTR_ID));
            DSTR_NM = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.DSTR_NM));
            DSTR_CNTCT_NM = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.DSTR_CNTCT_NM));
            ADD_1 = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.ADD_1));
            CITY = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.CITY));
            CTR_NM = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.CTR_NM));
            ZIP = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.ZIP));
            TELE = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.TELE));
            MOBILE = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.MOBILE));
            DSTR_INV = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.DSTR_INV));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.ACTIVE));
            EMAIL = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.EMAIL));
            USER = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.USER));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.StrDistributor.M_FLAG));

            // Try to retrieve the local entry from network entries
            found = networkEntriesStrDistributor.get(STORE_ID + DSTR_ID);
            if (found != null && M_FLAG == null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntriesStrDistributor.remove(STORE_ID + DSTR_ID);
            } else {

                strdstr.add(STORE_ID);
                strdstr.add(DSTR_ID);
                strdstr.add(DSTR_NM);
                strdstr.add(DSTR_CNTCT_NM);
                strdstr.add(ADD_1);
                strdstr.add(CITY);
                strdstr.add(CTR_NM);
                strdstr.add(ZIP);
                strdstr.add(TELE);
                strdstr.add(MOBILE);
                strdstr.add(DSTR_INV);
                strdstr.add(ACTIVE);
                strdstr.add(EMAIL);
                strdstr.add(USER);
                strdstr.add(S_FLAG);
                strdstr.add(POS_USER);
                strdstr.add(M_FLAG);
                strdstr.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbstrdistributor = new StringBuilder(128);
        for (String value : strdstr) {
            if (sbstrdistributor.length() > 0) {
                sbstrdistributor.append("|");
            }
            sbstrdistributor.append(value);
        }
        sbstrdistributor.insert(0, "|");
        HashMap<String, String> tempsalesdetailsreturn = new HashMap<>();
        tempsalesdetailsreturn.put("RETAIL_STR_DSTR", String.valueOf(sbstrdistributor));
        Log.d("*retail_str_dstr*", String.valueOf(sbstrdistributor));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_str_dstr_upload.jsp", tempsalesdetailsreturn);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_str_dstr");
            } else {
                Log.d("Response", success + " " + "retail_str_dstr");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_str_dstr......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.StrDistributor.CONTENT_URI_STR_DISTRIBUTOR, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    void syncstoreprodcpg(SyncResult syncstoreprodResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_store_prod_cpg.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_store_prod_cpg...");
        Map<String, StoreProdCpg> networkEntriesStoreProd = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonStoreProd = new JSONObject(jsonFeed);
        JSONArray jsonStoreProdArticles = jsonStoreProd.getJSONArray("RETAIL_STORE_PROD_CPG");
        for (int i = 0; i < jsonStoreProdArticles.length(); i++) {
            StoreProdCpg prodCpg = StoreProdCpgParser.parse(jsonStoreProdArticles.optJSONObject(i));
            networkEntriesStoreProd.put(prodCpg.getPROD_ID(), prodCpg);

        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.ProdCpg.CONTENT_URI_PROD_CPG, null, null, null, null, null);
        assert c != null;

        c.moveToFirst();
        StoreProdCpg found;

        for (int i = 0; i < c.getCount(); i++) {
            syncstoreprodResult.stats.numEntries++;
            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.STORE_ID));
            PROD_ID = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.PROD_ID));
            PROD_NM = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.PROD_NAME));
            BARCODE = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.BARCODE));
            MRP = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.MRP));
            S_PRICE = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.S_PRICE));
            P_PRICE = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.P_PRICE));
            TAX_DESC = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.TAX_DESC));
            PROD_HIER_DESC_3 = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.PROD_HIER_DESC_3));
            MFG = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.MFG));
            SELLING_ORDER_UNIT = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.SELLING_ORDER_UNIT));
            INTERNET_PRICE = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.INTERNET_PRICE));
            IS_PROD_REL_INT = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.IS_PROD_REL_INT));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.ACTIVE));
            C_IMG = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.C_IMG));
            PACK_UNIT_1 = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.PACK_UNIT_1));
            PACK_UNIT_2 = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.PACK_UNIT_2));
            PROFIT_MARGIN = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.PROFIT_MARGIN));
            USER = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.USER));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.S_FLAG));
            IND_NM = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.IND_NAME));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.M_FLAG));
            DISCOUNT_PERCENT = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.DISCOUNT_PERCENT));
            DISCOUNT_PERCENT_AMOUNT = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.DISCOUNT_PERCENT_AMOUNT));
            EX_PROD_ID = c.getString(c.getColumnIndex(ArticleContract.ProdCpg.EX_PROD_ID));
            // Try to retrieve the local entry from network entries
            found = networkEntriesStoreProd.get(PROD_ID + PROD_NM);
            if (found != null && found.getACTIVE().equals(ACTIVE) && found.getP_PRICE().equals(P_PRICE) && found.getS_PRICE().equals(S_PRICE) && found.getDISCOUNT_PERCENT().equals(DISCOUNT_PERCENT) && found.getMRP().equals(MRP) && found.getSELLING_ORDER_UNIT().equals(SELLING_ORDER_UNIT) && found.getPOS_USER().equals(POS_USER)) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntriesStoreProd.remove(PROD_ID + PROD_NM);
            } else {

                storeprod.add(STORE_ID);
                storeprod.add(PROD_ID);
                storeprod.add(PROD_NM);
                storeprod.add(BARCODE);
                storeprod.add(MRP);
                storeprod.add(S_PRICE);
                storeprod.add(P_PRICE);
                storeprod.add(SELLING_ORDER_UNIT);
                storeprod.add(INTERNET_PRICE);
                storeprod.add(IS_PROD_REL_INT);
                storeprod.add(TAX_DESC);
                storeprod.add(PROD_HIER_DESC_3);
                storeprod.add(PACK_UNIT_1);
                storeprod.add(PACK_UNIT_2);
                storeprod.add(MFG);
                storeprod.add(ACTIVE);
                storeprod.add(C_IMG);
                storeprod.add(PROFIT_MARGIN);
                storeprod.add(USER);
                storeprod.add(S_FLAG);
                storeprod.add(IND_NM);
                storeprod.add(POS_USER);
                storeprod.add(M_FLAG);
                storeprod.add(DISCOUNT_PERCENT);
                storeprod.add(DISCOUNT_PERCENT_AMOUNT);
                storeprod.add(EX_PROD_ID);
                storeprod.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempStoreProd = new StringBuilder(128);
        for (String value : storeprod) {
            if (sbtempStoreProd.length() > 0) {
                sbtempStoreProd.append("|");
            }
            sbtempStoreProd.append(value);
        }
        sbtempStoreProd.insert(0, "|");
        HashMap<String, String> tempstoreprod = new HashMap<>();
        tempstoreprod.put("RETAIL_STORE_PROD_CPG", String.valueOf(sbtempStoreProd));
        Log.d("*retail_str_prod_cpg*", String.valueOf(sbtempStoreProd));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_store_prod_cpg_upload.jsp", tempstoreprod);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_store_prod_cpg");
            } else {
                Log.d("Response", success + " " + "retail_store_prod_cpg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_store_prod_cpg......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.ProdCpg.CONTENT_URI_PROD_CPG, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    void synctopproduct(SyncResult synctopproductResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_top_product.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_top_product...");
        Map<String, TopProduct> networkEntriesTopProduct = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonTopProduct = new JSONObject(jsonFeed);
        JSONArray jsonTopProductArticles = jsonTopProduct.getJSONArray("RETAIL_TOP_PRODUCT");
        for (int i = 0; i < jsonTopProductArticles.length(); i++) {
            TopProduct tempTopProduct = TopProductParser.parse(jsonTopProductArticles.optJSONObject(i));
            networkEntriesTopProduct.put(tempTopProduct.getPROD_ID() + tempTopProduct.getPROD_NM(), tempTopProduct);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.TopProduct.CONTENT_URI_TOP_PRODUCT, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        TopProduct foundtempTopProduct;

        for (int i = 0; i < c.getCount(); i++) {
            synctopproductResult.stats.numEntries++;
            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.TopProduct.STORE_ID));
            PROD_ID = c.getString(c.getColumnIndex(ArticleContract.TopProduct.PROD_ID));
            PROD_NM = c.getString(c.getColumnIndex(ArticleContract.TopProduct.PROD_NM));
            PROD_SHORT_NM = c.getString(c.getColumnIndex(ArticleContract.TopProduct.PROD_SHORT_NM));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.TopProduct.S_FLAG));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.TopProduct.ACTIVE));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.TopProduct.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.TopProduct.M_FLAG));
            // Try to retrieve the local entry from network entries
            foundtempTopProduct = networkEntriesTopProduct.get(PROD_ID + PROD_NM);

            if (foundtempTopProduct != null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntriesTopProduct.remove(PROD_ID + PROD_NM);
            } else {
                topproduct.add(STORE_ID);
                topproduct.add(PROD_ID);
                topproduct.add(PROD_NM);
                topproduct.add(PROD_SHORT_NM);
                topproduct.add(S_FLAG);
                topproduct.add(ACTIVE);
                topproduct.add(POS_USER);
                topproduct.add(M_FLAG);
                topproduct.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempTopProduct = new StringBuilder(128);
        for (String value : topproduct) {
            if (sbtempTopProduct.length() > 0) {
                sbtempTopProduct.append("|");
            }
            sbtempTopProduct.append(value);
        }
        sbtempTopProduct.insert(0, "|");
        HashMap<String, String> temptopproduct = new HashMap<>();
        temptopproduct.put("RETAIL_TOP_PRODUCT", String.valueOf(sbtempTopProduct));
        Log.d("*Retail_top_product*", String.valueOf(sbtempTopProduct));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_top_product_upload.jsp", temptopproduct);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_top_product");
            } else {
                Log.d("Resposnse", success + " " + "retail_top_product");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_top_product......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.TopProduct.CONTENT_URI_TOP_PRODUCT, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    void syncemployees(SyncResult syncemployeesResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_employees.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_employees...");
        Map<String, Employees> networkEntriesEmployees = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonEmployees = new JSONObject(jsonFeed);
        JSONArray jsonEmployeesArticles = jsonEmployees.getJSONArray("RETAIL_EMPLOYEES");
        for (int i = 0; i < jsonEmployeesArticles.length(); i++) {
            Employees tempEmployees = EmployeesParser.parse(jsonEmployeesArticles.optJSONObject(i));
            networkEntriesEmployees.put(tempEmployees.getLAST_NAME(), tempEmployees);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.Employees.CONTENT_URI_EMPLOYEES, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        Employees foundtempEmployees;
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < c.getCount(); i++) {
            syncemployeesResult.stats.numEntries++;
            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.Employees.STORE_ID));
            USR_NM = c.getString(c.getColumnIndex(ArticleContract.Employees.USR_NM));
            FIRST_NAME = c.getString(c.getColumnIndex(ArticleContract.Employees.FIRST_NAME));
            LAST_NAME = c.getString(c.getColumnIndex(ArticleContract.Employees.LAST_NAME));
            PASSWORD = c.getString(c.getColumnIndex(ArticleContract.Employees.PASSWORD));
            CONFIRM_PASSWORD = c.getString(c.getColumnIndex(ArticleContract.Employees.CONFIRM_PASSWORD));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.Employees.ACTIVE));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.Employees.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.Employees.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.Employees.M_FLAG));
            // Try to retrieve the local entry from network entries
            foundtempEmployees = networkEntriesEmployees.get(STORE_ID);

            if (foundtempEmployees != null) {

                networkEntriesEmployees.remove(STORE_ID);
            } else {

                employees.add(STORE_ID);
                employees.add(USR_NM);
                employees.add(FIRST_NAME);
                employees.add(LAST_NAME);
                employees.add(PASSWORD);
                employees.add(CONFIRM_PASSWORD);
                employees.add(ACTIVE);
                employees.add(S_FLAG);
                employees.add(POS_USER);
                employees.add(M_FLAG);
                employees.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempEmployees = new StringBuilder(128);
        for (String value : employees) {
            if (sbtempEmployees.length() > 0) {
                sbtempEmployees.append("|");
            }
            sbtempEmployees.append(value);
        }
        sbtempEmployees.insert(0, "|");
        HashMap<String, String> tempemployees = new HashMap<>();
        tempemployees.put("RETAIL_EMPLOYEES", String.valueOf(sbtempEmployees));
        Log.d("*retail_employees*", String.valueOf(sbtempEmployees));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_employees_upload.jsp", tempemployees);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_employees");
            } else {
                Log.d("Response", success + " " + "retail_employees");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_employees......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.Employees.CONTENT_URI_EMPLOYEES, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    void synccustreject(SyncResult synccustrejectResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_store_cust_reject.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_store_cust_reject...");
        Map<String, RetailStoreCustReject> networkEntriesCustReject = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonCustReject = new JSONObject(jsonFeed);
        JSONArray jsonCustRejectArticles = jsonCustReject.getJSONArray("RETAIL_STORE_CUST_REJECT");
        for (int i = 0; i < jsonCustRejectArticles.length(); i++) {
            RetailStoreCustReject tempCustReject = RetailStrCustRejectParser.parse(jsonCustRejectArticles.optJSONObject(i));
            networkEntriesCustReject.put(tempCustReject.getSTORE_ID(), tempCustReject);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.CustReject.CONTENT_URI_CUST_REJECT, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailStoreCustReject found;
        for (int i = 0; i < c.getCount(); i++) {
            synccustrejectResult.stats.numEntries++;
            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.CustReject.STORE_ID));
            ID = c.getString(c.getColumnIndex(ArticleContract.CustReject.ID));
            REASON_FOR_REJECTION = c.getString(c.getColumnIndex(ArticleContract.CustReject.REASON_FOR_REJECTION));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.CustReject.S_FLAG));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.CustReject.ACTIVE));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.CustReject.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.CustReject.M_FLAG));

            found = networkEntriesCustReject.get(STORE_ID);

            if (found != null) {

                networkEntriesCustReject.remove(STORE_ID);

            } else {

                custreject.add(STORE_ID);
                custreject.add(ID);
                custreject.add(REASON_FOR_REJECTION);
                custreject.add(S_FLAG);
                custreject.add(ACTIVE);
                custreject.add(POS_USER);
                custreject.add(M_FLAG);
                custreject.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempCustReject = new StringBuilder(128);
        for (String value : custreject) {
            if (sbtempCustReject.length() > 0) {
                sbtempCustReject.append("|");
            }
            sbtempCustReject.append(value);
        }
        sbtempCustReject.insert(0, "|");
        HashMap<String, String> tempvendreject = new HashMap<>();
        tempvendreject.put("RETAIL_STORE_CUST_REJECT", String.valueOf(sbtempCustReject));
        Log.d("*Retail_str_cust_rejct*", String.valueOf(sbtempCustReject));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_store_cust_reject_upload.jsp", tempvendreject);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_store_cust_reject");
            } else {
                Log.d("Response", success + " " + "retail_store_cust_reject");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_store_cust_reject......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.CustReject.CONTENT_URI_CUST_REJECT, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network

    }

    void syncvendreject(SyncResult syncvendrejectResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_store_vend_reject.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_store_vend_reject...");
        Map<String, StoreVendReject> networkEntriesVendReject = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonVendReject = new JSONObject(jsonFeed);
        JSONArray jsonVendRejectArticles = jsonVendReject.getJSONArray("RETAIL_STORE_VEND_REJECT");
        for (int i = 0; i < jsonVendRejectArticles.length(); i++) {
            StoreVendReject tempVendReject = StoreVendRejectParser.parse(jsonVendRejectArticles.optJSONObject(i));
            networkEntriesVendReject.put(tempVendReject.getSTORE_ID(), tempVendReject);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.VendReject.CONTENT_URI_VEND_REJECT, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        StoreVendReject found;
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < c.getCount(); i++) {
            syncvendrejectResult.stats.numEntries++;
            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.VendReject.STORE_ID));
            ID = c.getString(c.getColumnIndex(ArticleContract.VendReject.ID));
            REASON_FOR_REJECTION = c.getString(c.getColumnIndex(ArticleContract.VendReject.REASON_FOR_REJECTION));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.VendReject.S_FLAG));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.VendReject.ACTIVE));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.VendReject.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.VendReject.M_FLAG));

            found = networkEntriesVendReject.get(STORE_ID);

            if (found != null) {
                networkEntriesVendReject.remove(STORE_ID);
            } else {

                vendreject.add(STORE_ID);
                vendreject.add(ID);
                vendreject.add(REASON_FOR_REJECTION);
                vendreject.add(S_FLAG);
                vendreject.add(ACTIVE);
                vendreject.add(POS_USER);
                vendreject.add(M_FLAG);
                vendreject.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempVendReject = new StringBuilder(128);
        for (String value : vendreject) {
            if (sbtempVendReject.length() > 0) {
                sbtempVendReject.append("|");
            }
            sbtempVendReject.append(value);
        }
        sbtempVendReject.insert(0, "|");
        HashMap<String, String> tempvendreject = new HashMap<>();
        tempvendreject.put("RETAIL_STORE_VEND_REJECT", String.valueOf(sbtempVendReject));
        Log.d("*retail_str_vend_rejct*", String.valueOf(sbtempVendReject));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_store_vend_reject_upload.jsp", tempvendreject);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_store_vend_reject");
            } else {
                Log.d("Response", success + " " + "retail_store_vend_reject");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_store_vend_reject......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.VendReject.CONTENT_URI_VEND_REJECT, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    void syncStoreMaint(SyncResult syncstoremaintResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_store_maint.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries from retail_store_maint...");
        Map<String, StoreMaint> networkEntriesStoreMaint = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonStoreMaint = new JSONObject(jsonFeed);
        JSONArray jsonStoreMaintArticles = jsonStoreMaint.getJSONArray("RETAIL_STORE_MAINT");
        for (int i = 0; i < jsonStoreMaintArticles.length(); i++) {
            StoreMaint tempStoreMaint = StoreMaintParser.parse(jsonStoreMaintArticles.optJSONObject(i));
            networkEntriesStoreMaint.put(tempStoreMaint.getSTORE_ID(), tempStoreMaint);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.StoreMaintain.CONTENT_URI_STOREMAINTAIN, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        StoreMaint foundtempStoreMaint;

        for (int i = 0; i < c.getCount(); i++) {
            syncstoremaintResult.stats.numEntries++;
            // Create local article entry
            TICKET_ID = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.TICKET_ID));
            SUPPORT_TICKET_STATUS = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.SUPPORT_TICKET_STATUS));
            TEAM_GROUP = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.TEAM_GROUP));
            TEAM_MEMBER = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.TEAM_MEMBER));
            COMMENT = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.COMMENT));
            STATUS = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.STATUS));
            USER = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.USER));
            DATE = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.DATE));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.M_FLAG));
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.STORE_ID));
            SUBJECT_DESC = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.SUBJECT_DESC));
            SUPPORT_PRIORITY = c.getString(c.getColumnIndex(ArticleContract.StoreMaintain.SUPPORT_PRIORITY));
            // Try to retrieve the local entry from network entries
            foundtempStoreMaint = networkEntriesStoreMaint.get(STORE_ID);

            if (foundtempStoreMaint != null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntriesStoreMaint.remove(STORE_ID);
                // Check to see if it needs to be updated
            } else {
                storemaint.add(TICKET_ID);
                storemaint.add(STORE_ID);
                storemaint.add(SUBJECT_DESC);
                storemaint.add(SUPPORT_PRIORITY);
                storemaint.add(SUPPORT_TICKET_STATUS);
                storemaint.add(TEAM_GROUP);
                storemaint.add(TEAM_MEMBER);
                storemaint.add(COMMENT);
                storemaint.add(STATUS);
                storemaint.add(USER);
                storemaint.add(DATE);
                storemaint.add("1");
                storemaint.add(POS_USER);
                storemaint.add(M_FLAG);
                storemaint.add(";");
            }
            c.moveToNext();
        }
        c.close();
        StringBuilder sbtempStoreMaint = new StringBuilder(128);
        for (String value : storemaint) {
            if (sbtempStoreMaint.length() > 0) {
                sbtempStoreMaint.append("|");
            }
            sbtempStoreMaint.append(value);
        }
        sbtempStoreMaint.insert(0, "|");
        HashMap<String, String> tempstoremaint = new HashMap<>();
        tempstoremaint.put("RETAIL_STORE_MAINT", String.valueOf(sbtempStoreMaint));
        Log.d("*retail_store_maint*", String.valueOf(sbtempStoreMaint));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_store_maint_upload.jsp", tempstoremaint);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_store_maint");
            } else {
                Log.d("Response", success + " " + "retail_store_maint");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update Fetching server entries from retail_store_maint......");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.StoreMaintain.CONTENT_URI_STOREMAINTAIN, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncRetailstrdecimal(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_store_decimal.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, RetailStoreDecimal> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_STORE_DECIMAL");
        for (int i = 0; i < jsonArticles.length(); i++) {
            RetailStoreDecimal retailStoreDecimal = RetailStoredecimalParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(retailStoreDecimal.getSTORE_ID(), retailStoreDecimal);
        }

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.StoreDecimal.CONTENT_URI_RETAIL_STORE_DECIMAL, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailStoreDecimal found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.STORE_ID));
            MRP_DECIMAL = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.MRP_DECIMAL));
            P_PRICE_DECIMAL = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.P_PRICE_DECIMAL));
            S_PRICE_DECIMAL = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.S_PRICE_DECIMAL));
            HOLD_PO = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.HOLD_PO));
            HOLD_INV = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.HOLD_INV));
            HOLD_SALES = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.HOLD_SALES));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.S_FLAG));
            ROUND_OFF = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.ROUND_OFF));
            SALES_HOLD_NO = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.SALES_HOLD_NO));
            PURCHASE_HOLD_NO = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.PURCHASE_HOLD_NO));
            INV_HOLD_NO = c.getString(c.getColumnIndex(ArticleContract.StoreDecimal.INV_HOLD_NO));
            M_FLAG = null;
            POS_USER = "User1";


            // Try to retrieve the local entry from network entries
            found = networkEntries.get(STORE_ID);

            storedecimal.add(STORE_ID);
            storedecimal.add(MRP_DECIMAL);
            storedecimal.add(P_PRICE_DECIMAL);
            storedecimal.add(S_PRICE_DECIMAL);
            storedecimal.add(HOLD_PO);
            storedecimal.add(HOLD_INV);
            storedecimal.add(HOLD_SALES);
            storedecimal.add("1");
            storedecimal.add(ROUND_OFF);
            storedecimal.add(SALES_HOLD_NO);
            storedecimal.add(PURCHASE_HOLD_NO);
            storedecimal.add(INV_HOLD_NO);
            storedecimal.add(POS_USER);
            storedecimal.add(M_FLAG);
            storedecimal.add(";");

            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : storedecimal) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_STORE_DECIMAL", String.valueOf(sb40));
        Log.d("*retail_str_decimal*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_store_decimal_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_store_decimal");
            } else {
                Log.d("Response", success + " " + "retail_store_decimal");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.StoreDecimal.CONTENT_URI_RETAIL_STORE_DECIMAL, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncDayopenclose(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_str_day_open_close.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, DayOpenClose> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_STR_DAY_OPEN_CLOSE");
        for (int i = 0; i < jsonArticles.length(); i++) {
            DayOpenClose dayOpenClose = DayOpenCloseParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(dayOpenClose.getSTORE_ID(), dayOpenClose);
        }

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.DayOpenClose.CONTENT_URI_DAY_OPEN_CLOSE, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        DayOpenClose found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry
            TRI_ID = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.TRI_ID));
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.STORE_ID));
            BUSINESS_DATE = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.BUSINESS_DATE));
            START_DATE = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.START_DATE));
            START_CASH = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.START_CASH));
            START_TIME = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.START_TIME));
            CBCASH_ONHAND = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.CBCASH_ONHAND));
            START_USER_ID = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.START_USER_ID));
            OPCASH_ONHAND = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.OPCASH_ONHAND));
            OP_CUR = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.OP_CUR));
            END_TIME = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.END_TIME));
            END_DATE = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.END_DATE));
            END_USER_ID = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.END_USER_ID));
            CB_CUR = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.CB_CUR));
            STATUS = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.STATUS));
            REMARKS = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.REMARKS));
            FLAG = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.FLAG));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.DayOpenClose.M_FLAG));

            // Try to retrieve the local entry from network entries
            found = networkEntries.get(STORE_ID);

          /*  if (found != null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntries.remove(STORE_ID);
            } else {*/

            dayopenclose.add(TRI_ID);
            dayopenclose.add(STORE_ID);
            dayopenclose.add(BUSINESS_DATE);
            dayopenclose.add(START_DATE);
            dayopenclose.add(START_CASH);
            dayopenclose.add(CBCASH_ONHAND);
            dayopenclose.add(START_TIME);
            dayopenclose.add(START_USER_ID);
            dayopenclose.add(OPCASH_ONHAND);
            dayopenclose.add(OP_CUR);
            dayopenclose.add(END_DATE);
            dayopenclose.add(END_TIME);
            dayopenclose.add(END_USER_ID);
            dayopenclose.add(CB_CUR);
            dayopenclose.add(STATUS);
            dayopenclose.add(REMARKS);
            dayopenclose.add(FLAG);
            dayopenclose.add(S_FLAG);
            dayopenclose.add(POS_USER);
            dayopenclose.add(M_FLAG);
            dayopenclose.add(";");
            //  }
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : dayopenclose) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_STR_DAY_OPEN_CLOSE", String.valueOf(sb40));
        Log.d("*Retail_str_day_close*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_str_day_open_close_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "retail_str_day_open_close");
            } else {
                Log.d("Response", success + " " + "retail_str_day_open_close");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.DayOpenClose.CONTENT_URI_DAY_OPEN_CLOSE, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncRetailbilldisplay(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_bill_display.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, RetailBillDisplay> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_BILL_DISPLAY");
        for (int i = 0; i < jsonArticles.length(); i++) {
            RetailBillDisplay retailBillDisplay = RetailBillDisplayParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(retailBillDisplay.getSTORE_ID(), retailBillDisplay);
        }

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.BillDisplay.CONTENT_URI_RETAIL_BILL_DISPLAY, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailBillDisplay found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry

            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.STORE_ID));
            TOTAL_BILL_VALUE = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.TOTAL_BILL_VALUE));
            DISCOUNT = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.DISCOUNT));
            NET_BILL_PAYABLE = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.NET_BILL_PAYABLE));
            AMOUNT_RECEIVED = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.AMOUNT_RECEIVED));
            AMOUNT_PAID_BACK = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.AMOUNT_PAID_BACK));
            FOOTER = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.FOOTER));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.BillDisplay.M_FLAG));

            // Try to retrieve the local entry from network entries
            found = networkEntries.get(STORE_ID);

           /* if (found != null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntries.remove(STORE_ID);
            } else {*/
            billdisplay.add(STORE_ID);
            billdisplay.add(TOTAL_BILL_VALUE);
            billdisplay.add(DISCOUNT);
            billdisplay.add(NET_BILL_PAYABLE);
            billdisplay.add(AMOUNT_RECEIVED);
            billdisplay.add(AMOUNT_PAID_BACK);
            billdisplay.add(FOOTER);
            billdisplay.add(S_FLAG);
            billdisplay.add(POS_USER);
            billdisplay.add(M_FLAG);
            billdisplay.add(";");
            // }
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : billdisplay) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_BILL_DISPLAY", String.valueOf(sb40));
        Log.d("*retail_bill_display*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_bill_display_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_bill_display");
            } else {
                Log.d("Response", success + " " + "retail_bill_display");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.BillDisplay.CONTENT_URI_RETAIL_BILL_DISPLAY, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncRetailbillvisible(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_bill_visible.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, RetailBillVisible> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_BILL_VISIBLE");
        for (int i = 0; i < jsonArticles.length(); i++) {
            RetailBillVisible retailBillVisible = RetailBillVisibleParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(retailBillVisible.getSTORE_ID(), retailBillVisible);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.BillVisible.CONTENT_URI_RETAIL_BILL_VISIBLE, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailBillVisible found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry

            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.BillVisible.STORE_ID));
            STORE_NAME = c.getString(c.getColumnIndex(ArticleContract.BillVisible.STORE_NAME));
            STORE_ADDRESS = c.getString(c.getColumnIndex(ArticleContract.BillVisible.STORE_ADDRESS));
            CITY = c.getString(c.getColumnIndex(ArticleContract.BillVisible.CITY));
            STORE_COUNTRY = c.getString(c.getColumnIndex(ArticleContract.BillVisible.STORE_COUNTRY));
            ZIP = c.getString(c.getColumnIndex(ArticleContract.BillVisible.ZIP));
            TELE_1 = c.getString(c.getColumnIndex(ArticleContract.BillVisible.TELE_1));
            TELE_2 = c.getString(c.getColumnIndex(ArticleContract.BillVisible.TELE_2));
            MAIN_BODY = c.getString(c.getColumnIndex(ArticleContract.BillVisible.MAIN_BODY));
            MRP = c.getString(c.getColumnIndex(ArticleContract.BillVisible.MRP));
            S_PRICE = c.getString(c.getColumnIndex(ArticleContract.BillVisible.S_PRICE));
            QTY = c.getString(c.getColumnIndex(ArticleContract.BillVisible.QTY));
            TOTAL = c.getString(c.getColumnIndex(ArticleContract.BillVisible.TOTAL));
            TOTAL_BILL_VALUE = c.getString(c.getColumnIndex(ArticleContract.BillVisible.TOTAL_BILL_VALUE));
            DISCOUNT = c.getString(c.getColumnIndex(ArticleContract.BillVisible.DISCOUNT));
            NET_BILL_PAYABLE = c.getString(c.getColumnIndex(ArticleContract.BillVisible.NET_BILL_PAYABLE));
            AMOUNT_RECEIVED = c.getString(c.getColumnIndex(ArticleContract.BillVisible.AMOUNT_RECEIVED));
            AMOUNT_PAID_BACK = c.getString(c.getColumnIndex(ArticleContract.BillVisible.AMOUNT_PAID_BACK));
            FOOTER = c.getString(c.getColumnIndex(ArticleContract.BillVisible.FOOTER));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.BillVisible.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.BillVisible.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.BillVisible.M_FLAG));
            NORMAL_SALES = c.getString(c.getColumnIndex(ArticleContract.BillVisible.NORMAL_SALES));
            CREDIT_CUSTOMER = c.getString(c.getColumnIndex(ArticleContract.BillVisible.CREDIT_CUSTOMER));
            RETURNS = c.getString(c.getColumnIndex(ArticleContract.BillVisible.RETURNS));
            SLAVE_FLAG = c.getString(c.getColumnIndex(ArticleContract.BillVisible.SLAVE_FLAG));
            MARGIN = c.getString(c.getColumnIndex(ArticleContract.BillVisible.MARGIN));
            FREE_GOODS = c.getString(c.getColumnIndex(ArticleContract.BillVisible.FREE_GOODS));
            INV_PRINT = c.getString(c.getColumnIndex(ArticleContract.BillVisible.INV_PRINT));
            DFCC = c.getString(c.getColumnIndex(ArticleContract.BillVisible.DFCC));
            // Try to retrieve the local entry from network entries
            found = networkEntries.get(STORE_ID);

          /*  if (found != null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntries.remove(STORE_ID);
            } else {*/

            billvisible.add(STORE_ID);
            billvisible.add(STORE_NAME);
            billvisible.add(STORE_ADDRESS);
            billvisible.add(CITY);
            billvisible.add(STORE_COUNTRY);
            billvisible.add(ZIP);
            billvisible.add(TELE_1);
            billvisible.add(TELE_2);
            billvisible.add(MAIN_BODY);
            billvisible.add(MRP);
            billvisible.add(S_PRICE);
            billvisible.add(QTY);
            billvisible.add(TOTAL);
            billvisible.add(TOTAL_BILL_VALUE);
            billvisible.add(DISCOUNT);
            billvisible.add(NET_BILL_PAYABLE);
            billvisible.add(AMOUNT_RECEIVED);
            billvisible.add(AMOUNT_PAID_BACK);
            billvisible.add(FOOTER);
            billvisible.add(S_FLAG);
            billvisible.add(POS_USER);
            billvisible.add(M_FLAG);
            billvisible.add(NORMAL_SALES);
            billvisible.add(CREDIT_CUSTOMER);
            billvisible.add(RETURNS);
            billvisible.add(SLAVE_FLAG);
            billvisible.add(MARGIN);
            billvisible.add(FREE_GOODS);
            billvisible.add(INV_PRINT);
            billvisible.add(DFCC);
            billvisible.add(";");
            //}
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : billvisible) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_BILL_VISIBLE", String.valueOf(sb40));
        Log.d("*Retail_bill_visible*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_bill_visible_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_bill_visible");
            } else {
                Log.d("Response", success + " " + "retail_bill_visible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.BillVisible.CONTENT_URI_RETAIL_BILL_VISIBLE, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncRetailcust(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_cust.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, RetailCust> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_CUST");
        for (int i = 0; i < jsonArticles.length(); i++) {
            RetailCust retailCust = RetailCustParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(retailCust.getSTORE_ID(), retailCust);
        }
        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.RetailCust.CONTENT_URI_RETAIL_CUST, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailCust found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry

            CUST_ID = c.getString(c.getColumnIndex(ArticleContract.RetailCust.CUST_ID));
            NAME = c.getString(c.getColumnIndex(ArticleContract.RetailCust.NAME));
            E_MAIL = c.getString(c.getColumnIndex(ArticleContract.RetailCust.E_MAIL));
            MOBILE_NO = c.getString(c.getColumnIndex(ArticleContract.RetailCust.MOBILE_NO));
            PASSWORD = c.getString(c.getColumnIndex(ArticleContract.RetailCust.PASSWORD));
            CREDIT_CUST = c.getString(c.getColumnIndex(ArticleContract.RetailCust.CREDIT_CUST));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.RetailCust.S_FLAG));
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.RetailCust.STORE_ID));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.RetailCust.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.RetailCust.M_FLAG));
            CUST_ADD = c.getString(c.getColumnIndex(ArticleContract.RetailCust.CUST_ADD));

            // Try to retrieve the local entry from network entries
            found = networkEntries.get(STORE_ID);

            if (found != null) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntries.remove(STORE_ID);

            } else {
                retailcust.add(CUST_ID);
                retailcust.add(NAME);
                retailcust.add(E_MAIL);
                retailcust.add(MOBILE_NO);
                retailcust.add(PASSWORD);
                retailcust.add(CREDIT_CUST);
                retailcust.add(S_FLAG);
                retailcust.add(STORE_ID);
                retailcust.add(POS_USER);
                retailcust.add(M_FLAG);
                retailcust.add(CUST_ADD);
                retailcust.add(";");

            }
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : retailcust) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_CUST", String.valueOf(sb40));
        Log.d("*Retail_cust*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_cust_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "retail_cust");

            } else {
                Log.d("Response", success + " " + "retail_cust");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.RetailCust.CONTENT_URI_RETAIL_CUST, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncRetailProdCom(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/Retail_store_prod_com.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, RetailStoreProdCom> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_STORE_PROD_COM");
        for (int i = 0; i < jsonArticles.length(); i++) {
            RetailStoreProdCom prodCom = RetailStoreProdComParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(prodCom.getPROD_ID(), prodCom);
        }

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.ProdCom.CONTENT_URI_PROD_COM, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailStoreProdCom found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.ProdCom.STORE_ID));
            DSTR_NM = c.getString(c.getColumnIndex(ArticleContract.ProdCom.DSTR_NM));
            IND_NM = c.getString(c.getColumnIndex(ArticleContract.ProdCom.IND_NAME));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.ProdCom.ACTIVE));
            P_PRICE = c.getString(c.getColumnIndex(ArticleContract.ProdCom.P_PRICE));
            DISCOUNT_PERCENT_AMOUNT = c.getString(c.getColumnIndex(ArticleContract.ProdCom.DISCOUNT_PERCENT_AMOUNT));
            BARCODE = c.getString(c.getColumnIndex(ArticleContract.ProdCom.BARCODE));
            MRP = c.getString(c.getColumnIndex(ArticleContract.ProdCom.MRP));
            IS_PROD_REL_INT = c.getString(c.getColumnIndex(ArticleContract.ProdCom.IS_PROD_REL_INT));
            S_PRICE = c.getString(c.getColumnIndex(ArticleContract.ProdCom.S_PRICE));
            SELLING_ORDER_UNIT = c.getString(c.getColumnIndex(ArticleContract.ProdCom.SELLING_ORDER_UNIT));
            INTERNET_PRICE = c.getString(c.getColumnIndex(ArticleContract.ProdCom.INTERNET_PRICE));
            PROD_ID = c.getString(c.getColumnIndex(ArticleContract.ProdCom.PROD_ID));
            DISCOUNT_PERCENT = c.getString(c.getColumnIndex(ArticleContract.ProdCom.DISCOUNT_PERCENT));
            PROD_NM = c.getString(c.getColumnIndex(ArticleContract.ProdCom.PROD_NAME));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.ProdCom.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.ProdCom.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.ProdCom.M_FLAG));
            PROFIT_MARGIN = c.getString(c.getColumnIndex(ArticleContract.ProdCom.PROFIT_MARGIN));
            EX_PROD_ID = c.getString(c.getColumnIndex(ArticleContract.ProdCom.EX_PROD_ID));
            // Try to retrieve the local entry from network entries
            found = networkEntries.get(PROD_ID);
            if (found != null && found.getACTIVE().equals(ACTIVE) && found.getP_PRICE().equals(P_PRICE) && found.getS_PRICE().equals(S_PRICE) && found.getDISCOUNT_PERCENT().equals(DISCOUNT_PERCENT) && found.getMRP().equals(MRP) && found.getSELLING_ORDER_UNIT().equals(SELLING_ORDER_UNIT) && found.getPOS_USER().equals(POS_USER)) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntries.remove(PROD_ID);
            } else {
                storeprodcom.add(STORE_ID);
                storeprodcom.add(PROD_ID);
                storeprodcom.add(PROD_NM);
                storeprodcom.add(BARCODE);
                storeprodcom.add(MRP);
                storeprodcom.add(S_PRICE);
                storeprodcom.add(P_PRICE);
                storeprodcom.add(SELLING_ORDER_UNIT);
                storeprodcom.add(INTERNET_PRICE);
                storeprodcom.add(IS_PROD_REL_INT);
                storeprodcom.add(ACTIVE);
                storeprodcom.add(PROFIT_MARGIN);
                storeprodcom.add(S_FLAG);
                storeprodcom.add(IND_NM);
                storeprodcom.add(POS_USER);
                storeprodcom.add(M_FLAG);
                storeprodcom.add(DISCOUNT_PERCENT);
                storeprodcom.add(DISCOUNT_PERCENT_AMOUNT);
                storeprodcom.add(DSTR_NM);
                storeprodcom.add(EX_PROD_ID);
                storeprodcom.add(";");
            }
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : storeprodcom) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_STORE_PROD_COM", String.valueOf(sb40));
        Log.d("*Retail_str_prod_com*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/Retail_store_prod_com_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Resposnse", success + " " + "Retail_store_prod_com");
            } else {
                Log.d("Response", success + " " + "Retail_str_prod_com");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.ProdCom.CONTENT_URI_PROD_COM, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    private void syncRetailStrVendor(SyncResult syncResult) throws IOException, JSONException, RemoteException, OperationApplicationException {
        String store_id = getStoreId();
        final String rssFeedEndpoint = "http://35.194.196.229:8080/FMCG_TESTING_SYNC/STR/retail_store_vendor.jsp?STORE_ID=" + store_id;

        // We need to collect all the network items in a hash table
        Log.i(TAG, "Fetching server entries...");
        Map<String, RetailStrVendor> networkEntries = new HashMap<>();

        // Parse the pretend json news feed
        String jsonFeed = download(rssFeedEndpoint);

        JSONObject jsonObject37 = new JSONObject(jsonFeed);
        JSONArray jsonArticles = jsonObject37.getJSONArray("RETAIL_STORE_VENDOR");
        for (int i = 0; i < jsonArticles.length(); i++) {
            RetailStrVendor strVendor = RetailStrVendorParser.parse(jsonArticles.optJSONObject(i));
            networkEntries.put(strVendor.getSTORE_ID(), strVendor);
        }

        // Create list for batching ContentProvider transactions
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Compare the hash table of network entries to all the local entries
        Log.i(TAG, "Fetching local entries...");
        Cursor c = resolver.query(ArticleContract.StoreVendor.CONTENT_URI_RETAIL_STORE_VENDOR, null, null, null, null, null);
        assert c != null;
        c.moveToFirst();

        RetailStrVendor found;
        for (int i = 0; i < c.getCount(); i++) {
            syncResult.stats.numEntries++;

            // Create local article entry
            STORE_ID = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.STORE_ID));
            VEND_ID = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.VEND_ID));
            VEND_NM = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.VEND_NM));
            VEND_CNTCT_NM = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.VEND_CNTCT_NM));
            ADD_1 = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.ADD_1));
            CITY = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.CITY));
            CTR_DESC = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.CTR_DESC));
            ZIP = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.ZIP));
            TELE = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.TELE));
            MOBILE = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.MOBILE));
            EMAIL = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.EMAIL));
            VEND_INV = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.VEND_INV));
            ACTIVE = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.ACTIVE));
            LAST_MODIFIED = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.LAST_MODIFIED));
            S_FLAG = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.S_FLAG));
            POS_USER = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.POS_USER));
            M_FLAG = c.getString(c.getColumnIndex(ArticleContract.StoreVendor.M_FLAG));
            // Try to retrieve the local entry from network entries
            found = networkEntries.get(STORE_ID);
            if (found != null && found.getACTIVE().equals(ACTIVE) && found.getPOS_USER().equals(POS_USER) && found.getVEND_NM().equals(VEND_NM) && found.getVEND_CNTCT_NM().equals(VEND_CNTCT_NM) && found.getMOBILE().equals(MOBILE) && found.getEMAIL().equals(EMAIL) && found.getVEND_INV().equals(VEND_INV)) {
                // The entry exists, remove from hash table to prevent re-inserting it
                networkEntries.remove(STORE_ID);
            } else {
                storevendor.add(STORE_ID);
                storevendor.add(VEND_ID);
                storevendor.add(VEND_NM);
                storevendor.add(VEND_CNTCT_NM);
                storevendor.add(ADD_1);
                storevendor.add(CITY);
                storevendor.add(CTR_DESC);
                storevendor.add(ZIP);
                storevendor.add(TELE);
                storevendor.add(MOBILE);
                storevendor.add(EMAIL);
                storevendor.add(VEND_INV);
                storevendor.add(ACTIVE);
                storevendor.add(S_FLAG);
                storevendor.add(POS_USER);
                storevendor.add(M_FLAG);
                storevendor.add(";");
            }
            c.moveToNext();
        }
        c.close();

        StringBuilder sb40 = new StringBuilder(128);
        for (String value : storevendor) {
            if (sb40.length() > 0) {
                sb40.append("|");
            }
            sb40.append(value);
        }
        sb40.insert(0, "|");
        HashMap<String, String> nets = new HashMap<>();
        nets.put("RETAIL_STORE_VENDOR", String.valueOf(sb40));
        Log.d("*retail_store_vendor*", String.valueOf(sb40));
        JSONParserSync jsonParserSync = new JSONParserSync();
        JSONObject s = jsonParserSync.sendPostRequest("http://35.194.196.229:8080/FMCG_TESTING_SYNC/WOCONN/retail_store_vendor_upload.jsp", nets);
        try {
            String success = s.getString("success");
            if (success.equals("1")) {
                Log.d("Response", success + " " + "Retail_store_vendor");
            } else {
                Log.d("Response", success + " " + "retail_store_vendor");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Synchronize by performing batch update
        Log.i(TAG, "Merge solution ready, applying batch update...");
        resolver.applyBatch(ArticleContract.CONTENT_AUTHORITY, batch);
        resolver.notifyChange(ArticleContract.StoreVendor.CONTENT_URI_RETAIL_STORE_VENDOR, // URI where data was modified
                null, // No local observer
                false); // IMPORTANT: Do not sync to network
    }

    @NonNull
    String download(String url) throws IOException {
        // Ensure we ALWAYS close these!
        HttpURLConnection client = null;
        InputStream is = null;

        try {
            // Connect to the server using GET protocol
            URL server = new URL(url);
            client = (HttpURLConnection) server.openConnection();
            client.connect();

            // Check for valid response code from the server
            int status = client.getResponseCode();
            is = (status == HttpURLConnection.HTTP_OK)
                    ? client.getInputStream() : client.getErrorStream();

            // Build the response or error as a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            for (String temp; ((temp = br.readLine()) != null); ) {
                sb.append(temp);
            }

            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
            if (client != null) {
                client.disconnect();
            }
        }
    }

}
