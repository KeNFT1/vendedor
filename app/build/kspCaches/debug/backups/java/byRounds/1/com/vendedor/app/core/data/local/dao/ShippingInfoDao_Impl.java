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
import com.vendedor.app.core.data.local.entity.ShippingInfoEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class ShippingInfoDao_Impl implements ShippingInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ShippingInfoEntity> __insertionAdapterOfShippingInfoEntity;

  private final EntityDeletionOrUpdateAdapter<ShippingInfoEntity> __updateAdapterOfShippingInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForItem;

  public ShippingInfoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfShippingInfoEntity = new EntityInsertionAdapter<ShippingInfoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `shipping_info` (`id`,`itemId`,`shipToName`,`shipToAddress`,`shipToCity`,`shipToState`,`shipToZip`,`trackingNumber`,`shippedAt`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShippingInfoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        if (entity.getShipToName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getShipToName());
        }
        if (entity.getShipToAddress() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getShipToAddress());
        }
        if (entity.getShipToCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getShipToCity());
        }
        if (entity.getShipToState() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getShipToState());
        }
        if (entity.getShipToZip() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getShipToZip());
        }
        if (entity.getTrackingNumber() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getTrackingNumber());
        }
        if (entity.getShippedAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getShippedAt());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfShippingInfoEntity = new EntityDeletionOrUpdateAdapter<ShippingInfoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `shipping_info` SET `id` = ?,`itemId` = ?,`shipToName` = ?,`shipToAddress` = ?,`shipToCity` = ?,`shipToState` = ?,`shipToZip` = ?,`trackingNumber` = ?,`shippedAt` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShippingInfoEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        if (entity.getShipToName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getShipToName());
        }
        if (entity.getShipToAddress() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getShipToAddress());
        }
        if (entity.getShipToCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getShipToCity());
        }
        if (entity.getShipToState() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getShipToState());
        }
        if (entity.getShipToZip() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getShipToZip());
        }
        if (entity.getTrackingNumber() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getTrackingNumber());
        }
        if (entity.getShippedAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getShippedAt());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllForItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM shipping_info WHERE itemId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ShippingInfoEntity info,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfShippingInfoEntity.insertAndReturnId(info);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final ShippingInfoEntity info,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfShippingInfoEntity.handle(info);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForItem(final long itemId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllForItem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, itemId);
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
          __preparedStmtOfDeleteAllForItem.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ShippingInfoEntity>> getShippingInfoForItem(final long itemId) {
    final String _sql = "SELECT * FROM shipping_info WHERE itemId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"shipping_info"}, new Callable<List<ShippingInfoEntity>>() {
      @Override
      @NonNull
      public List<ShippingInfoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfShipToName = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToName");
          final int _cursorIndexOfShipToAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToAddress");
          final int _cursorIndexOfShipToCity = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToCity");
          final int _cursorIndexOfShipToState = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToState");
          final int _cursorIndexOfShipToZip = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToZip");
          final int _cursorIndexOfTrackingNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "trackingNumber");
          final int _cursorIndexOfShippedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "shippedAt");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ShippingInfoEntity> _result = new ArrayList<ShippingInfoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShippingInfoEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpShipToName;
            if (_cursor.isNull(_cursorIndexOfShipToName)) {
              _tmpShipToName = null;
            } else {
              _tmpShipToName = _cursor.getString(_cursorIndexOfShipToName);
            }
            final String _tmpShipToAddress;
            if (_cursor.isNull(_cursorIndexOfShipToAddress)) {
              _tmpShipToAddress = null;
            } else {
              _tmpShipToAddress = _cursor.getString(_cursorIndexOfShipToAddress);
            }
            final String _tmpShipToCity;
            if (_cursor.isNull(_cursorIndexOfShipToCity)) {
              _tmpShipToCity = null;
            } else {
              _tmpShipToCity = _cursor.getString(_cursorIndexOfShipToCity);
            }
            final String _tmpShipToState;
            if (_cursor.isNull(_cursorIndexOfShipToState)) {
              _tmpShipToState = null;
            } else {
              _tmpShipToState = _cursor.getString(_cursorIndexOfShipToState);
            }
            final String _tmpShipToZip;
            if (_cursor.isNull(_cursorIndexOfShipToZip)) {
              _tmpShipToZip = null;
            } else {
              _tmpShipToZip = _cursor.getString(_cursorIndexOfShipToZip);
            }
            final String _tmpTrackingNumber;
            if (_cursor.isNull(_cursorIndexOfTrackingNumber)) {
              _tmpTrackingNumber = null;
            } else {
              _tmpTrackingNumber = _cursor.getString(_cursorIndexOfTrackingNumber);
            }
            final Long _tmpShippedAt;
            if (_cursor.isNull(_cursorIndexOfShippedAt)) {
              _tmpShippedAt = null;
            } else {
              _tmpShippedAt = _cursor.getLong(_cursorIndexOfShippedAt);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ShippingInfoEntity(_tmpId,_tmpItemId,_tmpShipToName,_tmpShipToAddress,_tmpShipToCity,_tmpShipToState,_tmpShipToZip,_tmpTrackingNumber,_tmpShippedAt,_tmpCreatedAt);
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
  public Object getById(final long id, final Continuation<? super ShippingInfoEntity> $completion) {
    final String _sql = "SELECT * FROM shipping_info WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ShippingInfoEntity>() {
      @Override
      @Nullable
      public ShippingInfoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfShipToName = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToName");
          final int _cursorIndexOfShipToAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToAddress");
          final int _cursorIndexOfShipToCity = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToCity");
          final int _cursorIndexOfShipToState = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToState");
          final int _cursorIndexOfShipToZip = CursorUtil.getColumnIndexOrThrow(_cursor, "shipToZip");
          final int _cursorIndexOfTrackingNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "trackingNumber");
          final int _cursorIndexOfShippedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "shippedAt");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ShippingInfoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpShipToName;
            if (_cursor.isNull(_cursorIndexOfShipToName)) {
              _tmpShipToName = null;
            } else {
              _tmpShipToName = _cursor.getString(_cursorIndexOfShipToName);
            }
            final String _tmpShipToAddress;
            if (_cursor.isNull(_cursorIndexOfShipToAddress)) {
              _tmpShipToAddress = null;
            } else {
              _tmpShipToAddress = _cursor.getString(_cursorIndexOfShipToAddress);
            }
            final String _tmpShipToCity;
            if (_cursor.isNull(_cursorIndexOfShipToCity)) {
              _tmpShipToCity = null;
            } else {
              _tmpShipToCity = _cursor.getString(_cursorIndexOfShipToCity);
            }
            final String _tmpShipToState;
            if (_cursor.isNull(_cursorIndexOfShipToState)) {
              _tmpShipToState = null;
            } else {
              _tmpShipToState = _cursor.getString(_cursorIndexOfShipToState);
            }
            final String _tmpShipToZip;
            if (_cursor.isNull(_cursorIndexOfShipToZip)) {
              _tmpShipToZip = null;
            } else {
              _tmpShipToZip = _cursor.getString(_cursorIndexOfShipToZip);
            }
            final String _tmpTrackingNumber;
            if (_cursor.isNull(_cursorIndexOfTrackingNumber)) {
              _tmpTrackingNumber = null;
            } else {
              _tmpTrackingNumber = _cursor.getString(_cursorIndexOfTrackingNumber);
            }
            final Long _tmpShippedAt;
            if (_cursor.isNull(_cursorIndexOfShippedAt)) {
              _tmpShippedAt = null;
            } else {
              _tmpShippedAt = _cursor.getLong(_cursorIndexOfShippedAt);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ShippingInfoEntity(_tmpId,_tmpItemId,_tmpShipToName,_tmpShipToAddress,_tmpShipToCity,_tmpShipToState,_tmpShipToZip,_tmpTrackingNumber,_tmpShippedAt,_tmpCreatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
