package com.vie.space.core.enume;

public enum CategoryEnum {
    PROGRAMMING_TECH(1, "Programming Tech"),
    DEVELOPMENT_LOGS(2, "Dev Logs"),
    ESSAY(3, "Essay"),
    TRAVELOGUE(4, "Travelogue"),
    POETRY(5, "Poetry"),
    PHOTOGRAPHY(6, "Photography");

    private final long categoryId;
    private final String category;

    CategoryEnum(long categoryId, String category) {
        this.categoryId = categoryId;
        this.category = category;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getCategory() {
        return category;
    }

    public static CategoryEnum getByCategoryId(long categoryId) {
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            if (categoryEnum.getCategoryId() == categoryId) {
                return categoryEnum;
            }
        }
        return null; // 如果找不到对应的 categoryId，则返回 null 或者抛出异常，具体情况视业务需求而定
    }
}
