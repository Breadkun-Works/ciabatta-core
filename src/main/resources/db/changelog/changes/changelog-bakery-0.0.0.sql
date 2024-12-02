-- liquibase formatted sql

-- changeset chanki5451:2024-12-02-001-create-bakery-menu context:local,dev,master labels:create
-- comment: BakeryMenu 테이블 생성

-- BakeryMenu 테이블 생성
create table public.bakery_menu
(
    id             bigint generated always as identity primary key,
    served_at      date         not null,
    name           varchar(70)  not null,
    description    varchar(255),
    image_filename varchar(100) not null,
    image_url      varchar(255) not null,
    created_at     timestamp    not null,
    created_by_id  varchar(36)  not null,
    updated_at     timestamp,
    updated_by_id  varchar(36)
);
comment on column public.bakery_menu.id is '베이커리 메뉴 ID';
comment on column public.bakery_menu.served_at is '제공일';
comment on column public.bakery_menu.name is '메뉴 이름';
comment on column public.bakery_menu.description is '메뉴 설명';
comment on column public.bakery_menu.image_filename is '이미지 파일 이름';
comment on column public.bakery_menu.image_url is '이미지 URL 경로';
comment on column public.bakery_menu.created_at is '메뉴 생성 시각';
comment on column public.bakery_menu.created_by_id is '메뉴 생성자 ID';
comment on column public.bakery_menu.updated_at is '메뉴 수정 시각';
comment on column public.bakery_menu.updated_by_id is '메뉴 수정자 ID';