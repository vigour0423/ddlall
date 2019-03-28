package com.ddl.learn.guava.cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class CacheLoaderTest3 {

    @Test
    public void testLoadNullValue() {
        CacheLoader<String, Employee> cacheLoader = CacheLoader
                .from(
                        k -> Objects.equals(k, "null") ? null : new Employee(k, k, k)
                );

        LoadingCache<String, Employee> loadingCache = CacheBuilder
                .newBuilder()
                .build(cacheLoader);

        Employee alex = loadingCache.getUnchecked("Alex");
        assertThat(alex, notNullValue());
        assertThat(alex.getName(), equalTo("Alex"));
        try {
            assertThat(loadingCache.getUnchecked("null"), nullValue());
            fail("should not process to here.");
        } catch (Exception e) {
            assertThat(e instanceof CacheLoader.InvalidCacheLoadException, equalTo(true));
        }
    }

    @Test
    public void testLoadNullValueUseOptional() {
        CacheLoader<String, Optional<Employee>> loader = new CacheLoader<String, Optional<Employee>>() {
            @Override
            public Optional<Employee> load(String key) {
                if (key.equals("null")) {
                    return Optional.fromNullable(null);
                } else {
                    return Optional.fromNullable(new Employee(key, key, key));
                }
            }
        };

        LoadingCache<String, Optional<Employee>> cache = CacheBuilder
                .newBuilder()
                .build(loader);

        assertThat(cache.getUnchecked("Alex").get(), notNullValue());
        assertThat(cache.getUnchecked("null").orNull(), nullValue());

        Employee def = cache.getUnchecked("null")
                .or(new Employee("default", "default", "default"));

        assertThat(def.getName().length(), equalTo(7));
    }


    @Test
    public void testCacheRefresh() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);

        CacheLoader<String, Long> cacheLoader = CacheLoader
                .from(k ->
                {
                    counter.incrementAndGet();
                    return System.currentTimeMillis();
                });

        LoadingCache<String, Long> cache = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(2, TimeUnit.SECONDS)
                .build(cacheLoader);

        Long result1 = cache.getUnchecked("Alex");

        TimeUnit.SECONDS.sleep(3);
        Long result2 = cache.getUnchecked("Alex");
        assertThat(counter.get(), equalTo(2));
        assertThat(result1.longValue() != result2.longValue(), equalTo(true));
    }

    @Test
    public void testCachePreLoad() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        Map<String, String> preData = new HashMap<String, String>() {
            {
                put("come", "COME");
                put("hello", "hello");
            }
        };

        cache.putAll(preData);
        assertThat(cache.size(), equalTo(2L));
        assertThat(cache.getUnchecked("alex"), equalTo("ALEX"));
        assertThat(cache.getUnchecked("hello"), equalTo("hello"));
    }

    @Test
    public void testCacheRemovedNotification() {
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);

        //移除监听器
        RemovalListener<String, String> listener = notification ->
        {
            if (notification.wasEvicted()) {
                RemovalCause cause = notification.getCause();
                assertThat(cause, is(RemovalCause.SIZE));
                assertThat(notification.getKey(), equalTo("KangKang"));
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(loader);

        cache.getUnchecked("KangKang");
        cache.getUnchecked("Mery");
        cache.getUnchecked("Jack");
        cache.getUnchecked("Jenny");
    }
}
