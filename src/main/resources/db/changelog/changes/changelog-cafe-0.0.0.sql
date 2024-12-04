-- liquibase formatted sql

-- changeset chanki5451:2024-10-28-001-create-cafe-menu context:local,dev,master labels:create
-- comment: CafeMenu 테이블 생성

-- CafeMenu 테이블 생성
create table public.cafe_menu
(
    id                bigint generated always as identity primary key,
    cafe_location     varchar(50)  not null,
    name              varchar(70)  not null,
    price             int          not null,
    deposit           int          not null,
    category          varchar(50)  not null,
    drink_temperature varchar(10)  not null,
    available         boolean      not null,
    description       varchar(255),
    image_filename    varchar(100) not null,
    image_url         varchar(255) not null,
    created_at        timestamp    not null,
    created_by_id     varchar(36)  not null,
    updated_at        timestamp,
    updated_by_id     varchar(36)
);
comment on column public.cafe_menu.id is '카페 메뉴 ID';
comment on column public.cafe_menu.cafe_location is '카페 위치';
comment on column public.cafe_menu.name is '메뉴 이름';
comment on column public.cafe_menu.price is '메뉴 가격';
comment on column public.cafe_menu.deposit is '다회용컵 보증금';
comment on column public.cafe_menu.category is '메뉴 카테고리';
comment on column public.cafe_menu.drink_temperature is '음료 제공 온도';
comment on column public.cafe_menu.available is '판매 가능 여부';
comment on column public.cafe_menu.description is '메뉴 설명';
comment on column public.cafe_menu.image_filename is '이미지 파일 이름';
comment on column public.cafe_menu.image_url is '이미지 URL 경로';
comment on column public.cafe_menu.created_at is '메뉴 생성 시각';
comment on column public.cafe_menu.created_by_id is '메뉴 생성자 ID';
comment on column public.cafe_menu.updated_at is '메뉴 수정 시각';
comment on column public.cafe_menu.updated_by_id is '메뉴 수정자 ID';

-- changeset chanki5451:2024-10-28-002-create-ulid-extension context:local,dev,master labels:create
-- comment: ULID 확장 활성화

create extension if not exists "ulid";

-- changeset chanki5451:2024-10-28-003-create-cafe-cart context:local,dev,master labels:create
-- comment: CafeCart 테이블 생성

-- CafeCart 테이블 생성
create table public.cafe_cart
(
    id            ulid         not null default gen_ulid() primary key,
    cafe_location varchar(50)  not null,
    title         varchar(70)  not null,
    description   varchar(255),
    created_at    timestamp    not null,
    expires_at    timestamp    not null,
    created_by_id varchar(36)  not null
);
comment on column public.cafe_cart.id is '카페 장바구니 ID';
comment on column public.cafe_cart.cafe_location is '카페 이름';
comment on column public.cafe_cart.title is '장바구니 제목';
comment on column public.cafe_cart.description is '장바구니 설명';
comment on column public.cafe_cart.created_at is '장바구니 생성 시간';
comment on column public.cafe_cart.expires_at is '장바구니 만료 시간';
comment on column public.cafe_cart.created_by_id is '작성자 ID';

-- changeset chanki5451:2024-10-28-004-create-cafe-cart-item context:local,dev,master labels:create
-- comment: CafeCartItem 테이블 생성

-- CafeCartItem 테이블 생성
create table public.cafe_cart_item
(
    id              ulid                                    not null default gen_ulid() primary key,
    cafe_cart_id    ulid references public.cafe_cart (id)   not null,
    cafe_menu_id    bigint references public.cafe_menu (id) not null,
    is_personal_cup boolean                                 not null,
    quantity        int                                     not null,
    created_at      timestamp                               not null,
    created_by_id   varchar(36)                             not null,
    created_by_name varchar(30)                             not null
);
comment on column public.cafe_cart_item.id is '카페 장바구니 항목 ID';
comment on column public.cafe_cart_item.cafe_cart_id is '참조된 카페 장바구니 ID';
comment on column public.cafe_cart_item.cafe_menu_id is '참조된 카페 메뉴 항목 ID';
comment on column public.cafe_cart_item.is_personal_cup is '개인컵 사용 여부';
comment on column public.cafe_cart_item.quantity is '담긴 수량';
comment on column public.cafe_cart_item.created_at is '항목 생성 시각';
comment on column public.cafe_cart_item.created_by_id is '작성자 ID';
comment on column public.cafe_cart_item.created_by_name is '작성자 이름';