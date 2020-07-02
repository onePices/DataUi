/*     */ package com.listen.redis;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
///*     */ import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
///*     */ import org.apache.log4j.Logger;
///*     */ import redis.clients.jedis.Jedis;
///*     */ import redis.clients.jedis.JedisPool;
///*     */ import redis.clients.jedis.JedisPoolConfig;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedisUtil
/*     */ {
///*     */   private static JedisPool jedisPool;
///*  23 */   private static Logger log = Logger.getLogger(RedisUtil.class);
///*     */   
///*     */   private static Properties prop;
///*     */   
///*     */   static  {
///*  28 */     InputStream in = null;
///*     */     try {
///*  30 */       prop = new Properties();
///*  31 */       in = RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties");
///*  32 */       prop.load(in);
///*     */     }
///*  34 */     catch (FileNotFoundException e) {
///*  35 */       log.error("RedisUtil-配置文件未找到", e);
///*  36 */     } catch (IOException e) {
///*  37 */       log.error("RedisUtil-出现IOException异常", e);
///*     */     } finally {
///*     */       try {
///*  40 */         if (in != null) {
///*  41 */           in.close();
///*     */         }
///*  43 */       } catch (Exception e) {
///*  44 */         log.error("RedisUtil-流关闭异常", e);
///*     */       } 
///*     */     } 
///*     */ 
///*     */ 
///*     */ 
///*     */     
///*  51 */     initialPool();
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   private static JedisPoolConfig initial() {
///*  59 */     JedisPoolConfig config = new JedisPoolConfig();
///*  60 */     config.setMaxTotal(Integer.parseInt(prop.getProperty("redis.maxActive")));
///*  61 */     config.setMaxIdle(Integer.parseInt(prop.getProperty("redis.maxIdle")));
///*  62 */     config.setNumTestsPerEvictionRun(10);
///*  63 */     config.setTimeBetweenEvictionRunsMillis(10L);
///*  64 */     config.setMinIdle(20);
///*  65 */     config.setSoftMinEvictableIdleTimeMillis(100L);
///*  66 */     config.setMaxWaitMillis(Long.parseLong(prop.getProperty("redis.maxWait")));
///*  67 */     config.setTestOnBorrow(Boolean.parseBoolean(prop.getProperty("redis.borrow")));
///*  68 */     config.setTestOnReturn(Boolean.parseBoolean(prop.getProperty("redis.borrow")));
///*  69 */     config.setTestWhileIdle(true);
///*  70 */     return config;
///*     */   }
///*     */ 
///*     */ 
///*     */   
///*     */   private static synchronized void initialPool() {
///*  76 */     jedisPool = new JedisPool((GenericObjectPoolConfig)initial(), 
///*  77 */         prop.getProperty("redis.ip"), 
///*  78 */         Integer.parseInt(prop.getProperty("redis.port")), 
///*  79 */         Integer.parseInt(prop.getProperty("redis.timeout")), 
///*  80 */         prop.getProperty("redis.password"), 
///*  81 */         1);
///*     */   }
///*     */ 
///*     */ 
///*     */   
///*     */   public static boolean exists(String key) {
///*  87 */     Jedis jedis = null;
///*  88 */     boolean flag = false;
///*     */     try {
///*  90 */       jedis = getJedis();
///*  91 */       flag = jedis.exists(key).booleanValue();
///*  92 */     } catch (Exception e) {
///*  93 */       log.debug("设置失败.", e);
///*  94 */       return flag;
///*     */     } finally {
///*  96 */       getColse(jedis);
///*     */     } 
///*  98 */     return flag;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void incrTask(String taskKey, long num) {
///* 107 */     Jedis jedis = null;
///*     */     try {
///* 109 */       jedis = getJedis();
///* 110 */       String value = jedis.get(taskKey);
///* 111 */       long count = (value == null || value == "") ? 0L : Long.valueOf(value).longValue();
///* 112 */       if (count + num >= 0L) {
///* 113 */         jedis.incrBy(taskKey, num);
///*     */       }
///* 115 */     } catch (Exception e) {
///* 116 */       log.debug("设置失败.", e);
///*     */     } finally {
///* 118 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */   
///* 122 */   public static void main(String[] args) { System.out.println(getHValue("123456789987654").toString()); }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static Integer getTaskNum(String taskKey) {
///* 131 */     Jedis jedis = null;
///*     */     try {
///* 133 */       jedis = getJedis();
///* 134 */       String value = jedis.get(taskKey);
///* 135 */       return Integer.valueOf((value == null || value == "") ? 0 : Integer.valueOf(value).intValue());
///* 136 */     } catch (Exception e) {
///* 137 */       log.debug("获取失败.", e);
///* 138 */       return Integer.valueOf(0);
///*     */     } finally {
///* 140 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static Jedis getJedis() {
///*     */     try {
///* 150 */       if (jedisPool == null) {
///* 151 */         initialPool();
///*     */       }
///* 153 */       return jedisPool.getResource();
///* 154 */     } catch (Exception e) {
///* 155 */       log.info("ListenSdkService 连接池连接异常", e);
///*     */       
///* 157 */       return null;
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void setLPush(String key, String... code) {
///* 166 */     Jedis jedis = null;
///*     */     try {
///* 168 */       jedis = getJedis();
///* 169 */       jedis.rpush(key, code);
///* 170 */     } catch (Exception e) {
///* 171 */       log.debug("设置失败.", e);
///*     */     } finally {
///* 173 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static List<String> getAndRmCode(String key) {
///* 182 */     List<String> codes = null;
///* 183 */     Jedis jedis = null;
///*     */     try {
///* 185 */       jedis = getJedis();
///* 186 */       codes = jedis.lrange(key, 0L, -1L);
///* 187 */       if (codes != null && codes.size() > 0) {
///* 188 */         jedis.ltrim(key, 0L, -(codes.size() + 1));
///*     */       }
///* 190 */       return codes;
///* 191 */     } catch (Exception e) {
///* 192 */       log.debug("获取失败.", e);
///* 193 */       return codes;
///*     */     } finally {
///* 195 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void disableTime(String key, int seconds) {
///* 208 */     Jedis jedis = null;
///*     */     
///*     */     try {
///* 211 */       jedis = getJedis();
///* 212 */       jedis.expire(key, seconds);
///*     */     
///*     */     }
///* 215 */     catch (Exception e) {
///* 216 */       log.debug("设置失效失败.", e);
///*     */     } finally {
///* 218 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void addHValue(String key, Map<String, String> value) {
///* 231 */     Jedis jedis = null;
///*     */     
///*     */     try {
///* 234 */       jedis = getJedis();
///* 235 */       jedis.hmset(key, value);
///*     */     }
///* 237 */     catch (Exception e) {
///* 238 */       log.debug("插入数据有异常.", e);
///*     */     } finally {
///* 240 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */   
///*     */   public static Map<String, String> getHValue(String key) {
///* 245 */     Jedis jedis = null;
///* 246 */     Map<String, String> keyVal = null;
///*     */     
///*     */     try {
///* 249 */       jedis = getJedis();
///* 250 */       keyVal = jedis.hgetAll(key);
///*     */     }
///* 252 */     catch (Exception e) {
///* 253 */       log.debug("插入数据有异常.", e);
///* 254 */       return keyVal;
///*     */     } finally {
///* 256 */       getColse(jedis);
///*     */     } 
///* 258 */     return keyVal;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static boolean addValue(String key, String value) {
///* 270 */     Jedis jedis = null;
///*     */     
///*     */     try {
///* 273 */       jedis = getJedis();
///* 274 */       String code = jedis.set(key, value);
///* 275 */       if (code.equals("ok"))
///*     */       {
///* 277 */         return true;
///*     */       }
///*     */     }
///* 280 */     catch (Exception e) {
///* 281 */       log.debug("插入数据有异常.", e);
///* 282 */       return false;
///*     */     } finally {
///* 284 */       getColse(jedis);
///*     */     } 
///* 286 */     return false;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static boolean delKey(String key) {
///* 296 */     Jedis jedis = null;
///*     */     
///*     */     try {
///* 299 */       jedis = getJedis();
///* 300 */       Long code = jedis.del(key);
///* 301 */       if (code.longValue() > 1L)
///*     */       {
///* 303 */         return true;
///*     */       }
///*     */     }
///* 306 */     catch (Exception e) {
///* 307 */       log.debug("删除key异常.", e);
///* 308 */       return false;
///*     */     } finally {
///* 310 */       getColse(jedis);
///*     */     } 
///* 312 */     return false;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static String getValue(String key) {
///* 323 */     Jedis jedis = null;
///*     */     
///*     */     try {
///* 326 */       jedis = getJedis();
///* 327 */       String value = jedis.get(key);
///* 328 */       if (value != null)
///*     */       {
///* 330 */         return value;
///*     */       }
///*     */     }
///* 333 */     catch (Exception e) {
///* 334 */       log.debug("读取数据有异常.", e);
///* 335 */       return "-9000";
///*     */     } finally {
///* 337 */       getColse(jedis);
///*     */     } 
///* 339 */     return null;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void getColse(Jedis jedis) {
///* 350 */     if (jedis != null)
///*     */     {
///* 352 */       jedis.close();
///*     */     }
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void addInfo(String key, String code) {
///* 361 */     Jedis jedis = null;
///*     */     try {
///* 363 */       jedis = getJedis();
///* 364 */       jedis.sadd(key, new String[] { code });
///* 365 */     } catch (Exception e) {
///* 366 */       log.debug("增加元素失败.", e);
///*     */     } finally {
///* 368 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static void delInfo(String key, String code) {
///* 377 */     Jedis jedis = null;
///*     */     try {
///* 379 */       jedis = getJedis();
///* 380 */       long count = jedis.srem(key, new String[] { code }).longValue();
///* 381 */     } catch (Exception e) {
///* 382 */       log.debug("删除元素失败.", e);
///*     */     } finally {
///* 384 */       getColse(jedis);
///*     */     } 
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static String getInfo(String key) {
///* 394 */     Jedis jedis = null;
///* 395 */     String value = null;
///*     */     try {
///* 397 */       jedis = getJedis();
///* 398 */       value = jedis.spop(key);
///* 399 */     } catch (Exception e) {
///* 400 */       log.debug("获取元素失败.", e);
///*     */     } finally {
///* 402 */       getColse(jedis);
///*     */     } 
///* 404 */     return value;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static Long getLen(String key) {
///* 412 */     Jedis jedis = null;
///* 413 */     Long len = null;
///*     */     try {
///* 415 */       jedis = getJedis();
///* 416 */       len = jedis.scard(key);
///* 417 */     } catch (Exception e) {
///* 418 */       log.debug("获取set的长度.", e);
///*     */     } finally {
///* 420 */       getColse(jedis);
///*     */     } 
///* 422 */     return len;
///*     */   }
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   
///*     */   public static Set<String> getAllInfo(String key) {
///* 431 */     Jedis jedis = null;
///* 432 */     Set<String> set = null;
///*     */     try {
///* 434 */       jedis = getJedis();
///* 435 */       set = jedis.smembers(key);
///* 436 */     } catch (Exception e) {
///* 437 */       log.debug("获取key的set的所有值", e);
///*     */     } finally {
///* 439 */       getColse(jedis);
///*     */     } 
///* 441 */     return set;
///*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\com.zip!\com\listen\redis\RedisUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.2
 */