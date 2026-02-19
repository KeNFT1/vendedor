package com.vendedor.app.core.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.vendedor.app.core.data.local.dao.ItemDao;
import com.vendedor.app.core.data.local.dao.ItemDao_Impl;
import com.vendedor.app.core.data.local.dao.ItemPhotoDao;
import com.vendedor.app.core.data.local.dao.ItemPhotoDao_Impl;
import com.vendedor.app.core.data.local.dao.ListingDao;
import com.vendedor.app.core.data.local.dao.ListingDao_Impl;
import com.vendedor.app.core.data.local.dao.PriceResearchDao;
import com.vendedor.app.core.data.local.dao.PriceResearchDao_Impl;
import com.vendedor.app.core.data.local.dao.ShippingInfoDao;
import com.vendedor.app.core.data.local.dao.ShippingInfoDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VendedorDatabase_Impl extends VendedorDatabase {
  private volatile ItemDao _itemDao;

  private volatile ItemPhotoDao _itemPhotoDao;

  private volatile ListingDao _listingDao;

  private volatile PriceResearchDao _priceResearchDao;

  private volatile ShippingInfoDao _shippingInfoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `items` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `category` TEXT, `subcategory` TEXT, `brand` TEXT, `condition` TEXT, `estimatedLengthInches` REAL, `estimatedWidthInches` REAL, `estimatedHeightInches` REAL, `estimatedWeightOz` REAL, `suggestedPriceLow` REAL, `suggestedPriceHigh` REAL, `askingPrice` REAL, `status` TEXT NOT NULL, `aiIdentificationJson` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `item_photos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemId` INTEGER NOT NULL, `filePath` TEXT NOT NULL, `isPrimary` INTEGER NOT NULL, `sortOrder` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`itemId`) REFERENCES `items`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_item_photos_itemId` ON `item_photos` (`itemId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `listings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemId` INTEGER NOT NULL, `marketplace` TEXT NOT NULL, `externalListingId` TEXT, `listingUrl` TEXT, `status` TEXT NOT NULL, `listedPrice` REAL, `listedAt` INTEGER, `updatedAt` INTEGER NOT NULL, FOREIGN KEY(`itemId`) REFERENCES `items`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_listings_itemId` ON `listings` (`itemId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `price_research` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemId` INTEGER NOT NULL, `source` TEXT NOT NULL, `query` TEXT NOT NULL, `averagePrice` REAL, `medianPrice` REAL, `lowPrice` REAL, `highPrice` REAL, `sampleSize` INTEGER NOT NULL, `resultsJson` TEXT, `researchedAt` INTEGER NOT NULL, FOREIGN KEY(`itemId`) REFERENCES `items`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_research_itemId` ON `price_research` (`itemId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `shipping_info` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemId` INTEGER NOT NULL, `shipToName` TEXT, `shipToAddress` TEXT, `shipToCity` TEXT, `shipToState` TEXT, `shipToZip` TEXT, `trackingNumber` TEXT, `shippedAt` INTEGER, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`itemId`) REFERENCES `items`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_shipping_info_itemId` ON `shipping_info` (`itemId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '16555224ac4df3f34c9d9a61b6c3310c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `items`");
        db.execSQL("DROP TABLE IF EXISTS `item_photos`");
        db.execSQL("DROP TABLE IF EXISTS `listings`");
        db.execSQL("DROP TABLE IF EXISTS `price_research`");
        db.execSQL("DROP TABLE IF EXISTS `shipping_info`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsItems = new HashMap<String, TableInfo.Column>(18);
        _columnsItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("subcategory", new TableInfo.Column("subcategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("brand", new TableInfo.Column("brand", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("condition", new TableInfo.Column("condition", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("estimatedLengthInches", new TableInfo.Column("estimatedLengthInches", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("estimatedWidthInches", new TableInfo.Column("estimatedWidthInches", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("estimatedHeightInches", new TableInfo.Column("estimatedHeightInches", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("estimatedWeightOz", new TableInfo.Column("estimatedWeightOz", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("suggestedPriceLow", new TableInfo.Column("suggestedPriceLow", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("suggestedPriceHigh", new TableInfo.Column("suggestedPriceHigh", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("askingPrice", new TableInfo.Column("askingPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("aiIdentificationJson", new TableInfo.Column("aiIdentificationJson", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItems = new TableInfo("items", _columnsItems, _foreignKeysItems, _indicesItems);
        final TableInfo _existingItems = TableInfo.read(db, "items");
        if (!_infoItems.equals(_existingItems)) {
          return new RoomOpenHelper.ValidationResult(false, "items(com.vendedor.app.core.data.local.entity.ItemEntity).\n"
                  + " Expected:\n" + _infoItems + "\n"
                  + " Found:\n" + _existingItems);
        }
        final HashMap<String, TableInfo.Column> _columnsItemPhotos = new HashMap<String, TableInfo.Column>(6);
        _columnsItemPhotos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemPhotos.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemPhotos.put("filePath", new TableInfo.Column("filePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemPhotos.put("isPrimary", new TableInfo.Column("isPrimary", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemPhotos.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItemPhotos.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItemPhotos = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysItemPhotos.add(new TableInfo.ForeignKey("items", "CASCADE", "NO ACTION", Arrays.asList("itemId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesItemPhotos = new HashSet<TableInfo.Index>(1);
        _indicesItemPhotos.add(new TableInfo.Index("index_item_photos_itemId", false, Arrays.asList("itemId"), Arrays.asList("ASC")));
        final TableInfo _infoItemPhotos = new TableInfo("item_photos", _columnsItemPhotos, _foreignKeysItemPhotos, _indicesItemPhotos);
        final TableInfo _existingItemPhotos = TableInfo.read(db, "item_photos");
        if (!_infoItemPhotos.equals(_existingItemPhotos)) {
          return new RoomOpenHelper.ValidationResult(false, "item_photos(com.vendedor.app.core.data.local.entity.ItemPhotoEntity).\n"
                  + " Expected:\n" + _infoItemPhotos + "\n"
                  + " Found:\n" + _existingItemPhotos);
        }
        final HashMap<String, TableInfo.Column> _columnsListings = new HashMap<String, TableInfo.Column>(9);
        _columnsListings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("marketplace", new TableInfo.Column("marketplace", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("externalListingId", new TableInfo.Column("externalListingId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("listingUrl", new TableInfo.Column("listingUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("listedPrice", new TableInfo.Column("listedPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("listedAt", new TableInfo.Column("listedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsListings.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysListings = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysListings.add(new TableInfo.ForeignKey("items", "CASCADE", "NO ACTION", Arrays.asList("itemId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesListings = new HashSet<TableInfo.Index>(1);
        _indicesListings.add(new TableInfo.Index("index_listings_itemId", false, Arrays.asList("itemId"), Arrays.asList("ASC")));
        final TableInfo _infoListings = new TableInfo("listings", _columnsListings, _foreignKeysListings, _indicesListings);
        final TableInfo _existingListings = TableInfo.read(db, "listings");
        if (!_infoListings.equals(_existingListings)) {
          return new RoomOpenHelper.ValidationResult(false, "listings(com.vendedor.app.core.data.local.entity.ListingEntity).\n"
                  + " Expected:\n" + _infoListings + "\n"
                  + " Found:\n" + _existingListings);
        }
        final HashMap<String, TableInfo.Column> _columnsPriceResearch = new HashMap<String, TableInfo.Column>(11);
        _columnsPriceResearch.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("query", new TableInfo.Column("query", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("averagePrice", new TableInfo.Column("averagePrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("medianPrice", new TableInfo.Column("medianPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("lowPrice", new TableInfo.Column("lowPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("highPrice", new TableInfo.Column("highPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("sampleSize", new TableInfo.Column("sampleSize", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("resultsJson", new TableInfo.Column("resultsJson", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceResearch.put("researchedAt", new TableInfo.Column("researchedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPriceResearch = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPriceResearch.add(new TableInfo.ForeignKey("items", "CASCADE", "NO ACTION", Arrays.asList("itemId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPriceResearch = new HashSet<TableInfo.Index>(1);
        _indicesPriceResearch.add(new TableInfo.Index("index_price_research_itemId", false, Arrays.asList("itemId"), Arrays.asList("ASC")));
        final TableInfo _infoPriceResearch = new TableInfo("price_research", _columnsPriceResearch, _foreignKeysPriceResearch, _indicesPriceResearch);
        final TableInfo _existingPriceResearch = TableInfo.read(db, "price_research");
        if (!_infoPriceResearch.equals(_existingPriceResearch)) {
          return new RoomOpenHelper.ValidationResult(false, "price_research(com.vendedor.app.core.data.local.entity.PriceResearchEntity).\n"
                  + " Expected:\n" + _infoPriceResearch + "\n"
                  + " Found:\n" + _existingPriceResearch);
        }
        final HashMap<String, TableInfo.Column> _columnsShippingInfo = new HashMap<String, TableInfo.Column>(10);
        _columnsShippingInfo.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("shipToName", new TableInfo.Column("shipToName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("shipToAddress", new TableInfo.Column("shipToAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("shipToCity", new TableInfo.Column("shipToCity", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("shipToState", new TableInfo.Column("shipToState", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("shipToZip", new TableInfo.Column("shipToZip", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("trackingNumber", new TableInfo.Column("trackingNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("shippedAt", new TableInfo.Column("shippedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippingInfo.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShippingInfo = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysShippingInfo.add(new TableInfo.ForeignKey("items", "CASCADE", "NO ACTION", Arrays.asList("itemId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesShippingInfo = new HashSet<TableInfo.Index>(1);
        _indicesShippingInfo.add(new TableInfo.Index("index_shipping_info_itemId", false, Arrays.asList("itemId"), Arrays.asList("ASC")));
        final TableInfo _infoShippingInfo = new TableInfo("shipping_info", _columnsShippingInfo, _foreignKeysShippingInfo, _indicesShippingInfo);
        final TableInfo _existingShippingInfo = TableInfo.read(db, "shipping_info");
        if (!_infoShippingInfo.equals(_existingShippingInfo)) {
          return new RoomOpenHelper.ValidationResult(false, "shipping_info(com.vendedor.app.core.data.local.entity.ShippingInfoEntity).\n"
                  + " Expected:\n" + _infoShippingInfo + "\n"
                  + " Found:\n" + _existingShippingInfo);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "16555224ac4df3f34c9d9a61b6c3310c", "28041a2424afcbb2a5b639482f6224f7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "items","item_photos","listings","price_research","shipping_info");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `items`");
      _db.execSQL("DELETE FROM `item_photos`");
      _db.execSQL("DELETE FROM `listings`");
      _db.execSQL("DELETE FROM `price_research`");
      _db.execSQL("DELETE FROM `shipping_info`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ItemDao.class, ItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ItemPhotoDao.class, ItemPhotoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ListingDao.class, ListingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PriceResearchDao.class, PriceResearchDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShippingInfoDao.class, ShippingInfoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ItemDao itemDao() {
    if (_itemDao != null) {
      return _itemDao;
    } else {
      synchronized(this) {
        if(_itemDao == null) {
          _itemDao = new ItemDao_Impl(this);
        }
        return _itemDao;
      }
    }
  }

  @Override
  public ItemPhotoDao itemPhotoDao() {
    if (_itemPhotoDao != null) {
      return _itemPhotoDao;
    } else {
      synchronized(this) {
        if(_itemPhotoDao == null) {
          _itemPhotoDao = new ItemPhotoDao_Impl(this);
        }
        return _itemPhotoDao;
      }
    }
  }

  @Override
  public ListingDao listingDao() {
    if (_listingDao != null) {
      return _listingDao;
    } else {
      synchronized(this) {
        if(_listingDao == null) {
          _listingDao = new ListingDao_Impl(this);
        }
        return _listingDao;
      }
    }
  }

  @Override
  public PriceResearchDao priceResearchDao() {
    if (_priceResearchDao != null) {
      return _priceResearchDao;
    } else {
      synchronized(this) {
        if(_priceResearchDao == null) {
          _priceResearchDao = new PriceResearchDao_Impl(this);
        }
        return _priceResearchDao;
      }
    }
  }

  @Override
  public ShippingInfoDao shippingInfoDao() {
    if (_shippingInfoDao != null) {
      return _shippingInfoDao;
    } else {
      synchronized(this) {
        if(_shippingInfoDao == null) {
          _shippingInfoDao = new ShippingInfoDao_Impl(this);
        }
        return _shippingInfoDao;
      }
    }
  }
}
