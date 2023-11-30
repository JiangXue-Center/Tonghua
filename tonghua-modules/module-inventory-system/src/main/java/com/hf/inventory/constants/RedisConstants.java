package com.hf.inventory.constants;

public interface RedisConstants {

    public static final String PRODUCT_INVENTORY_KEY = "sku:inventory:";

    public static final Long PRODUCT_INVENTORY_TTL = 1800L;

    public static final String LOCK_STOCK_KEY = "lock:stock:";

    public static final int LOCK_MAX_WAIT_TIME = 60;

    public static final int LOCK_EXPIRE_TTL = 30;

}
