package com.vendedor.app.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vendedor.app.core.data.local.entity.ItemEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemDao_Impl implements ItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ItemEntity> __insertionAdapterOfItemEntity;

  private final EntityDeletionOrUpdateAdapter<ItemEntity> __deletionAdapterOfItemEntity;

  private final EntityDeletionOrUpdateAdapter<ItemEntity> __updateAdapterOfItemEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public ItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItemEntity = new EntityInsertionAdapter<ItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `items` (`id`,`title`,`description`,`category`,`subcategory`,`brand`,`condition`,`estimatedLengthInches`,`estimatedWidthInches`,`estimatedHeightInches`,`estimatedWeightOz`,`suggestedPriceLow`,`suggestedPriceHigh`,`askingPrice`,`status`,`aiIdentificationJson`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        if (entity.getSubcategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSubcategory());
        }
        if (entity.getBrand() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBrand());
        }
        if (entity.getCondition() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCondition());
        }
        if (entity.getEstimatedLengthInches() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getEstimatedLengthInches());
        }
        if (entity.getEstimatedWidthInches() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getEstimatedWidthInches());
        }
        if (entity.getEstimatedHeightInches() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getEstimatedHeightInches());
        }
        if (entity.getEstimatedWeightOz() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getEstimatedWeightOz());
        }
        if (entity.getSuggestedPriceLow() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getSuggestedPriceLow());
        }
        if (entity.getSuggestedPriceHigh() == null) {
          statement.bindNull(13);
        } else {
          statement.bindDouble(13, entity.getSuggestedPriceHigh());
        }
        if (entity.getAskingPrice() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getAskingPrice());
        }
        statement.bindString(15, entity.getStatus());
        if (entity.getAiIdentificationJson() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getAiIdentificationJson());
        }
        statement.bindLong(17, entity.getCreatedAt());
        statement.bindLong(18, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfItemEntity = new EntityDeletionOrUpdateAdapter<ItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfItemEntity = new EntityDeletionOrUpdateAdapter<ItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `items` SET `id` = ?,`title` = ?,`description` = ?,`category` = ?,`subcategory` = ?,`brand` = ?,`condition` = ?,`estimatedLengthInches` = ?,`estimatedWidthInches` = ?,`estimatedHeightInches` = ?,`estimatedWeightOz` = ?,`suggestedPriceLow` = ?,`suggestedPriceHigh` = ?,`askingPrice` = ?,`status` = ?,`aiIdentificationJson` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ItemEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        if (entity.getCategory() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCategory());
        }
        if (entity.getSubcategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSubcategory());
        }
        if (entity.getBrand() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBrand());
        }
        if (entity.getCondition() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCondition());
        }
        if (entity.getEstimatedLengthInches() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getEstimatedLengthInches());
        }
        if (entity.getEstimatedWidthInches() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getEstimatedWidthInches());
        }
        if (entity.getEstimatedHeightInches() == null) {
          statement.bindNull(10);
        } else {
          statement.bindDouble(10, entity.getEstimatedHeightInches());
        }
        if (entity.getEstimatedWeightOz() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getEstimatedWeightOz());
        }
        if (entity.getSuggestedPriceLow() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getSuggestedPriceLow());
        }
        if (entity.getSuggestedPriceHigh() == null) {
          statement.bindNull(13);
        } else {
          statement.bindDouble(13, entity.getSuggestedPriceHigh());
        }
        if (entity.getAskingPrice() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getAskingPrice());
        }
        statement.bindString(15, entity.getStatus());
        if (entity.getAiIdentificationJson() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getAiIdentificationJson());
        }
        statement.bindLong(17, entity.getCreatedAt());
        statement.bindLong(18, entity.getUpdatedAt());
        statement.bindLong(19, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM items WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ItemEntity item, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfItemEntity.insertAndReturnId(item);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ItemEntity item, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfItemEntity.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ItemEntity item, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfItemEntity.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ItemEntity>> getAllItems() {
    final String _sql = "SELECT * FROM items ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"items"}, new Callable<List<ItemEntity>>() {
      @Override
      @NonNull
      public List<ItemEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subcategory");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfEstimatedLengthInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedLengthInches");
          final int _cursorIndexOfEstimatedWidthInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedWidthInches");
          final int _cursorIndexOfEstimatedHeightInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedHeightInches");
          final int _cursorIndexOfEstimatedWeightOz = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedWeightOz");
          final int _cursorIndexOfSuggestedPriceLow = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedPriceLow");
          final int _cursorIndexOfSuggestedPriceHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedPriceHigh");
          final int _cursorIndexOfAskingPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "askingPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAiIdentificationJson = CursorUtil.getColumnIndexOrThrow(_cursor, "aiIdentificationJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ItemEntity> _result = new ArrayList<ItemEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ItemEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpSubcategory;
            if (_cursor.isNull(_cursorIndexOfSubcategory)) {
              _tmpSubcategory = null;
            } else {
              _tmpSubcategory = _cursor.getString(_cursorIndexOfSubcategory);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final Float _tmpEstimatedLengthInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedLengthInches)) {
              _tmpEstimatedLengthInches = null;
            } else {
              _tmpEstimatedLengthInches = _cursor.getFloat(_cursorIndexOfEstimatedLengthInches);
            }
            final Float _tmpEstimatedWidthInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedWidthInches)) {
              _tmpEstimatedWidthInches = null;
            } else {
              _tmpEstimatedWidthInches = _cursor.getFloat(_cursorIndexOfEstimatedWidthInches);
            }
            final Float _tmpEstimatedHeightInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedHeightInches)) {
              _tmpEstimatedHeightInches = null;
            } else {
              _tmpEstimatedHeightInches = _cursor.getFloat(_cursorIndexOfEstimatedHeightInches);
            }
            final Float _tmpEstimatedWeightOz;
            if (_cursor.isNull(_cursorIndexOfEstimatedWeightOz)) {
              _tmpEstimatedWeightOz = null;
            } else {
              _tmpEstimatedWeightOz = _cursor.getFloat(_cursorIndexOfEstimatedWeightOz);
            }
            final Double _tmpSuggestedPriceLow;
            if (_cursor.isNull(_cursorIndexOfSuggestedPriceLow)) {
              _tmpSuggestedPriceLow = null;
            } else {
              _tmpSuggestedPriceLow = _cursor.getDouble(_cursorIndexOfSuggestedPriceLow);
            }
            final Double _tmpSuggestedPriceHigh;
            if (_cursor.isNull(_cursorIndexOfSuggestedPriceHigh)) {
              _tmpSuggestedPriceHigh = null;
            } else {
              _tmpSuggestedPriceHigh = _cursor.getDouble(_cursorIndexOfSuggestedPriceHigh);
            }
            final Double _tmpAskingPrice;
            if (_cursor.isNull(_cursorIndexOfAskingPrice)) {
              _tmpAskingPrice = null;
            } else {
              _tmpAskingPrice = _cursor.getDouble(_cursorIndexOfAskingPrice);
            }
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpAiIdentificationJson;
            if (_cursor.isNull(_cursorIndexOfAiIdentificationJson)) {
              _tmpAiIdentificationJson = null;
            } else {
              _tmpAiIdentificationJson = _cursor.getString(_cursorIndexOfAiIdentificationJson);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ItemEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpSubcategory,_tmpBrand,_tmpCondition,_tmpEstimatedLengthInches,_tmpEstimatedWidthInches,_tmpEstimatedHeightInches,_tmpEstimatedWeightOz,_tmpSuggestedPriceLow,_tmpSuggestedPriceHigh,_tmpAskingPrice,_tmpStatus,_tmpAiIdentificationJson,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getItemById(final long id, final Continuation<? super ItemEntity> $completion) {
    final String _sql = "SELECT * FROM items WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ItemEntity>() {
      @Override
      @Nullable
      public ItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subcategory");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfEstimatedLengthInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedLengthInches");
          final int _cursorIndexOfEstimatedWidthInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedWidthInches");
          final int _cursorIndexOfEstimatedHeightInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedHeightInches");
          final int _cursorIndexOfEstimatedWeightOz = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedWeightOz");
          final int _cursorIndexOfSuggestedPriceLow = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedPriceLow");
          final int _cursorIndexOfSuggestedPriceHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedPriceHigh");
          final int _cursorIndexOfAskingPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "askingPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAiIdentificationJson = CursorUtil.getColumnIndexOrThrow(_cursor, "aiIdentificationJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ItemEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpSubcategory;
            if (_cursor.isNull(_cursorIndexOfSubcategory)) {
              _tmpSubcategory = null;
            } else {
              _tmpSubcategory = _cursor.getString(_cursorIndexOfSubcategory);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final Float _tmpEstimatedLengthInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedLengthInches)) {
              _tmpEstimatedLengthInches = null;
            } else {
              _tmpEstimatedLengthInches = _cursor.getFloat(_cursorIndexOfEstimatedLengthInches);
            }
            final Float _tmpEstimatedWidthInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedWidthInches)) {
              _tmpEstimatedWidthInches = null;
            } else {
              _tmpEstimatedWidthInches = _cursor.getFloat(_cursorIndexOfEstimatedWidthInches);
            }
            final Float _tmpEstimatedHeightInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedHeightInches)) {
              _tmpEstimatedHeightInches = null;
            } else {
              _tmpEstimatedHeightInches = _cursor.getFloat(_cursorIndexOfEstimatedHeightInches);
            }
            final Float _tmpEstimatedWeightOz;
            if (_cursor.isNull(_cursorIndexOfEstimatedWeightOz)) {
              _tmpEstimatedWeightOz = null;
            } else {
              _tmpEstimatedWeightOz = _cursor.getFloat(_cursorIndexOfEstimatedWeightOz);
            }
            final Double _tmpSuggestedPriceLow;
            if (_cursor.isNull(_cursorIndexOfSuggestedPriceLow)) {
              _tmpSuggestedPriceLow = null;
            } else {
              _tmpSuggestedPriceLow = _cursor.getDouble(_cursorIndexOfSuggestedPriceLow);
            }
            final Double _tmpSuggestedPriceHigh;
            if (_cursor.isNull(_cursorIndexOfSuggestedPriceHigh)) {
              _tmpSuggestedPriceHigh = null;
            } else {
              _tmpSuggestedPriceHigh = _cursor.getDouble(_cursorIndexOfSuggestedPriceHigh);
            }
            final Double _tmpAskingPrice;
            if (_cursor.isNull(_cursorIndexOfAskingPrice)) {
              _tmpAskingPrice = null;
            } else {
              _tmpAskingPrice = _cursor.getDouble(_cursorIndexOfAskingPrice);
            }
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpAiIdentificationJson;
            if (_cursor.isNull(_cursorIndexOfAiIdentificationJson)) {
              _tmpAiIdentificationJson = null;
            } else {
              _tmpAiIdentificationJson = _cursor.getString(_cursorIndexOfAiIdentificationJson);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ItemEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpSubcategory,_tmpBrand,_tmpCondition,_tmpEstimatedLengthInches,_tmpEstimatedWidthInches,_tmpEstimatedHeightInches,_tmpEstimatedWeightOz,_tmpSuggestedPriceLow,_tmpSuggestedPriceHigh,_tmpAskingPrice,_tmpStatus,_tmpAiIdentificationJson,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ItemEntity>> getItemsByStatus(final String status) {
    final String _sql = "SELECT * FROM items WHERE status = ? ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, status);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"items"}, new Callable<List<ItemEntity>>() {
      @Override
      @NonNull
      public List<ItemEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "subcategory");
          final int _cursorIndexOfBrand = CursorUtil.getColumnIndexOrThrow(_cursor, "brand");
          final int _cursorIndexOfCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "condition");
          final int _cursorIndexOfEstimatedLengthInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedLengthInches");
          final int _cursorIndexOfEstimatedWidthInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedWidthInches");
          final int _cursorIndexOfEstimatedHeightInches = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedHeightInches");
          final int _cursorIndexOfEstimatedWeightOz = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedWeightOz");
          final int _cursorIndexOfSuggestedPriceLow = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedPriceLow");
          final int _cursorIndexOfSuggestedPriceHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedPriceHigh");
          final int _cursorIndexOfAskingPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "askingPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAiIdentificationJson = CursorUtil.getColumnIndexOrThrow(_cursor, "aiIdentificationJson");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ItemEntity> _result = new ArrayList<ItemEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ItemEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpSubcategory;
            if (_cursor.isNull(_cursorIndexOfSubcategory)) {
              _tmpSubcategory = null;
            } else {
              _tmpSubcategory = _cursor.getString(_cursorIndexOfSubcategory);
            }
            final String _tmpBrand;
            if (_cursor.isNull(_cursorIndexOfBrand)) {
              _tmpBrand = null;
            } else {
              _tmpBrand = _cursor.getString(_cursorIndexOfBrand);
            }
            final String _tmpCondition;
            if (_cursor.isNull(_cursorIndexOfCondition)) {
              _tmpCondition = null;
            } else {
              _tmpCondition = _cursor.getString(_cursorIndexOfCondition);
            }
            final Float _tmpEstimatedLengthInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedLengthInches)) {
              _tmpEstimatedLengthInches = null;
            } else {
              _tmpEstimatedLengthInches = _cursor.getFloat(_cursorIndexOfEstimatedLengthInches);
            }
            final Float _tmpEstimatedWidthInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedWidthInches)) {
              _tmpEstimatedWidthInches = null;
            } else {
              _tmpEstimatedWidthInches = _cursor.getFloat(_cursorIndexOfEstimatedWidthInches);
            }
            final Float _tmpEstimatedHeightInches;
            if (_cursor.isNull(_cursorIndexOfEstimatedHeightInches)) {
              _tmpEstimatedHeightInches = null;
            } else {
              _tmpEstimatedHeightInches = _cursor.getFloat(_cursorIndexOfEstimatedHeightInches);
            }
            final Float _tmpEstimatedWeightOz;
            if (_cursor.isNull(_cursorIndexOfEstimatedWeightOz)) {
              _tmpEstimatedWeightOz = null;
            } else {
              _tmpEstimatedWeightOz = _cursor.getFloat(_cursorIndexOfEstimatedWeightOz);
            }
            final Double _tmpSuggestedPriceLow;
            if (_cursor.isNull(_cursorIndexOfSuggestedPriceLow)) {
              _tmpSuggestedPriceLow = null;
            } else {
              _tmpSuggestedPriceLow = _cursor.getDouble(_cursorIndexOfSuggestedPriceLow);
            }
            final Double _tmpSuggestedPriceHigh;
            if (_cursor.isNull(_cursorIndexOfSuggestedPriceHigh)) {
              _tmpSuggestedPriceHigh = null;
            } else {
              _tmpSuggestedPriceHigh = _cursor.getDouble(_cursorIndexOfSuggestedPriceHigh);
            }
            final Double _tmpAskingPrice;
            if (_cursor.isNull(_cursorIndexOfAskingPrice)) {
              _tmpAskingPrice = null;
            } else {
              _tmpAskingPrice = _cursor.getDouble(_cursorIndexOfAskingPrice);
            }
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final String _tmpAiIdentificationJson;
            if (_cursor.isNull(_cursorIndexOfAiIdentificationJson)) {
              _tmpAiIdentificationJson = null;
            } else {
              _tmpAiIdentificationJson = _cursor.getString(_cursorIndexOfAiIdentificationJson);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ItemEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpSubcategory,_tmpBrand,_tmpCondition,_tmpEstimatedLengthInches,_tmpEstimatedWidthInches,_tmpEstimatedHeightInches,_tmpEstimatedWeightOz,_tmpSuggestedPriceLow,_tmpSuggestedPriceHigh,_tmpAskingPrice,_tmpStatus,_tmpAiIdentificationJson,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
