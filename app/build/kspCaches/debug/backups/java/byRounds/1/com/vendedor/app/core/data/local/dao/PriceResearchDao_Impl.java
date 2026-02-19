package com.vendedor.app.core.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vendedor.app.core.data.local.entity.PriceResearchEntity;
import java.lang.Class;
import java.lang.Double;
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
public final class PriceResearchDao_Impl implements PriceResearchDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PriceResearchEntity> __insertionAdapterOfPriceResearchEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForItem;

  public PriceResearchDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPriceResearchEntity = new EntityInsertionAdapter<PriceResearchEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `price_research` (`id`,`itemId`,`source`,`query`,`averagePrice`,`medianPrice`,`lowPrice`,`highPrice`,`sampleSize`,`resultsJson`,`researchedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceResearchEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getItemId());
        statement.bindString(3, entity.getSource());
        statement.bindString(4, entity.getQuery());
        if (entity.getAveragePrice() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getAveragePrice());
        }
        if (entity.getMedianPrice() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getMedianPrice());
        }
        if (entity.getLowPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getLowPrice());
        }
        if (entity.getHighPrice() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getHighPrice());
        }
        statement.bindLong(9, entity.getSampleSize());
        if (entity.getResultsJson() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getResultsJson());
        }
        statement.bindLong(11, entity.getResearchedAt());
      }
    };
    this.__preparedStmtOfDeleteAllForItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_research WHERE itemId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final PriceResearchEntity research,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPriceResearchEntity.insertAndReturnId(research);
          __db.setTransactionSuccessful();
          return _result;
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
  public Flow<List<PriceResearchEntity>> getResearchForItem(final long itemId) {
    final String _sql = "SELECT * FROM price_research WHERE itemId = ? ORDER BY researchedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"price_research"}, new Callable<List<PriceResearchEntity>>() {
      @Override
      @NonNull
      public List<PriceResearchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfQuery = CursorUtil.getColumnIndexOrThrow(_cursor, "query");
          final int _cursorIndexOfAveragePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "averagePrice");
          final int _cursorIndexOfMedianPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "medianPrice");
          final int _cursorIndexOfLowPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "lowPrice");
          final int _cursorIndexOfHighPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "highPrice");
          final int _cursorIndexOfSampleSize = CursorUtil.getColumnIndexOrThrow(_cursor, "sampleSize");
          final int _cursorIndexOfResultsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultsJson");
          final int _cursorIndexOfResearchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "researchedAt");
          final List<PriceResearchEntity> _result = new ArrayList<PriceResearchEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceResearchEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final String _tmpQuery;
            _tmpQuery = _cursor.getString(_cursorIndexOfQuery);
            final Double _tmpAveragePrice;
            if (_cursor.isNull(_cursorIndexOfAveragePrice)) {
              _tmpAveragePrice = null;
            } else {
              _tmpAveragePrice = _cursor.getDouble(_cursorIndexOfAveragePrice);
            }
            final Double _tmpMedianPrice;
            if (_cursor.isNull(_cursorIndexOfMedianPrice)) {
              _tmpMedianPrice = null;
            } else {
              _tmpMedianPrice = _cursor.getDouble(_cursorIndexOfMedianPrice);
            }
            final Double _tmpLowPrice;
            if (_cursor.isNull(_cursorIndexOfLowPrice)) {
              _tmpLowPrice = null;
            } else {
              _tmpLowPrice = _cursor.getDouble(_cursorIndexOfLowPrice);
            }
            final Double _tmpHighPrice;
            if (_cursor.isNull(_cursorIndexOfHighPrice)) {
              _tmpHighPrice = null;
            } else {
              _tmpHighPrice = _cursor.getDouble(_cursorIndexOfHighPrice);
            }
            final int _tmpSampleSize;
            _tmpSampleSize = _cursor.getInt(_cursorIndexOfSampleSize);
            final String _tmpResultsJson;
            if (_cursor.isNull(_cursorIndexOfResultsJson)) {
              _tmpResultsJson = null;
            } else {
              _tmpResultsJson = _cursor.getString(_cursorIndexOfResultsJson);
            }
            final long _tmpResearchedAt;
            _tmpResearchedAt = _cursor.getLong(_cursorIndexOfResearchedAt);
            _item = new PriceResearchEntity(_tmpId,_tmpItemId,_tmpSource,_tmpQuery,_tmpAveragePrice,_tmpMedianPrice,_tmpLowPrice,_tmpHighPrice,_tmpSampleSize,_tmpResultsJson,_tmpResearchedAt);
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
  public Object getLatestResearch(final long itemId,
      final Continuation<? super PriceResearchEntity> $completion) {
    final String _sql = "SELECT * FROM price_research WHERE itemId = ? ORDER BY researchedAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceResearchEntity>() {
      @Override
      @Nullable
      public PriceResearchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfQuery = CursorUtil.getColumnIndexOrThrow(_cursor, "query");
          final int _cursorIndexOfAveragePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "averagePrice");
          final int _cursorIndexOfMedianPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "medianPrice");
          final int _cursorIndexOfLowPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "lowPrice");
          final int _cursorIndexOfHighPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "highPrice");
          final int _cursorIndexOfSampleSize = CursorUtil.getColumnIndexOrThrow(_cursor, "sampleSize");
          final int _cursorIndexOfResultsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "resultsJson");
          final int _cursorIndexOfResearchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "researchedAt");
          final PriceResearchEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpItemId;
            _tmpItemId = _cursor.getLong(_cursorIndexOfItemId);
            final String _tmpSource;
            _tmpSource = _cursor.getString(_cursorIndexOfSource);
            final String _tmpQuery;
            _tmpQuery = _cursor.getString(_cursorIndexOfQuery);
            final Double _tmpAveragePrice;
            if (_cursor.isNull(_cursorIndexOfAveragePrice)) {
              _tmpAveragePrice = null;
            } else {
              _tmpAveragePrice = _cursor.getDouble(_cursorIndexOfAveragePrice);
            }
            final Double _tmpMedianPrice;
            if (_cursor.isNull(_cursorIndexOfMedianPrice)) {
              _tmpMedianPrice = null;
            } else {
              _tmpMedianPrice = _cursor.getDouble(_cursorIndexOfMedianPrice);
            }
            final Double _tmpLowPrice;
            if (_cursor.isNull(_cursorIndexOfLowPrice)) {
              _tmpLowPrice = null;
            } else {
              _tmpLowPrice = _cursor.getDouble(_cursorIndexOfLowPrice);
            }
            final Double _tmpHighPrice;
            if (_cursor.isNull(_cursorIndexOfHighPrice)) {
              _tmpHighPrice = null;
            } else {
              _tmpHighPrice = _cursor.getDouble(_cursorIndexOfHighPrice);
            }
            final int _tmpSampleSize;
            _tmpSampleSize = _cursor.getInt(_cursorIndexOfSampleSize);
            final String _tmpResultsJson;
            if (_cursor.isNull(_cursorIndexOfResultsJson)) {
              _tmpResultsJson = null;
            } else {
              _tmpResultsJson = _cursor.getString(_cursorIndexOfResultsJson);
            }
            final long _tmpResearchedAt;
            _tmpResearchedAt = _cursor.getLong(_cursorIndexOfResearchedAt);
            _result = new PriceResearchEntity(_tmpId,_tmpItemId,_tmpSource,_tmpQuery,_tmpAveragePrice,_tmpMedianPrice,_tmpLowPrice,_tmpHighPrice,_tmpSampleSize,_tmpResultsJson,_tmpResearchedAt);
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
