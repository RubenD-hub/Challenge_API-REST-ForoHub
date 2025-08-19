create table  respuestas (
    id bigint not null auto_increment,
    mensaje text not null,
    fecha_creacion datetime not null,
    topico bigint not null,
    autor bigint not null,

    primary key(id),
    foreign key(topico) references topicos(id),
    foreign key(autor) references usuarios(id)
);