package fc.server.palette.purchase.entity.type;

import lombok.Getter;

@Getter
public enum Category {
    HOUSEHOLD("생활용품"),
    FOODS("식품"),
    OFFICE_SUPPLIES("사무용품"),
    BEAUTY("뷰티"),
    TICKET("티켓/회원권"),
    FASHION("패션의류/잡화"),
    SPORTS_LEISURE("스포츠/레저"),
    BOOKS("도서"),
    ELECTRONICS("가전/디지털"),
    PROCESSED_WELLNESS_FOODS("가공/건강식품"),
    ETC("기타");

    private final String description;

    Category(String description){
        this.description = description;
    }
}

