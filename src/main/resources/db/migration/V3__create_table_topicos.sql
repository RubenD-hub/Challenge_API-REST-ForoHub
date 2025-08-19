CREATE TABLE topicos (
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensaje text not null,
    fecha_creacion datetime not null,
    status varchar(50),
    autor bigint not null ,
    curso bigint not null,

    primary key(id),
    foreign key(autor) references usuarios(id),
    foreign key(curso) references cursos(id)
);