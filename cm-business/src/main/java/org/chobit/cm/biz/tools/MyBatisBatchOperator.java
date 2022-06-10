package org.chobit.cm.biz.tools;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.function.BiFunction;


/**
 * @author robin
 */
public final class MyBatisBatchOperator {


    private static final int BATCH_SIZE = 100;

    /**
     * 批量处理修改或者插入
     *
     * @param data        需要被处理的数据
     * @param classMapper Mybatis的Mapper类
     * @param func        自定义处理逻辑
     * @return int 影响的总行数
     */
    public static <T, U, R> int operate(List<T> data, Class<U> classMapper, BiFunction<T, U, R> func) {
        int i = 1;
        SqlSessionFactory sqlSessionFactory = BeanKit.get(SqlSessionFactory.class);
        SqlSession batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            U mapper = batchSqlSession.getMapper(classMapper);
            int size = data.size();
            for (T ele : data) {
                func.apply(ele, mapper);
                if ((i % BATCH_SIZE == 0) || i == size) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            // 非事务环境下强制commit，事务情况下该commit相当于无效
            batchSqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
        } catch (Exception e) {
            batchSqlSession.rollback();
            throw e;
        } finally {
            batchSqlSession.close();
        }
        return i - 1;
    }


    private MyBatisBatchOperator() {
    }
}


