

Abaixo, SQLs de seleção e remoção da CONSTRAINT criada de forma indesejada.


select constraint_name from information_schema.constraint_column_usage

 where table_name = 'usuarios_acesso' and column_name = 'acesso_id'
 
 and constraint_name <> 'unique_acesso_user'.
 
 
 OBS: resultado do SQL acima é: uk8bak9jswon2id2jbunuqlfl9e 
 
 
 
 
 
 alter table usuarios_acesso drop constraint "uk8bak9jswon2id2jbunuqlfl9e"; 
 