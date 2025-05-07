
CREATE TABLE public.tabela_acesso_end_potin
(
  qtd_acesso_end_point integer,
  nome_end_point character varying,
  CONSTRAINT nome_end_point_unique UNIQUE (nome_end_point)
);