package jdev.mentoria.lojavirtual;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jdev.mentoria.lojavirtual.dto.ObjetoErroDTO;

   

   @RestControllerAdvice
   public class ControleExcecoes extends ResponseEntityExceptionHandler {
	
	
	
	@ExceptionHandler({ExceptionMentoriaJava.class})
	public ResponseEntity<Object> handleExceptionInternalCustom(ExceptionMentoriaJava ex){
	
	ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
	
	objetoErroDTO.setError(ex.getMessage());
	
	objetoErroDTO.setCode(HttpStatus.OK.toString());
	
	
	return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.OK);	
		
		
		
	}
	
	

	
	@Override
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class }) // Array com Excecoes que podem ser capturadas																			 
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

		String msg = "";

		if (ex instanceof MethodArgumentNotValidException) {

			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

			for (ObjectError objectError : list) {

				msg += objectError.getDefaultMessage() + "\n";

			}

		}if(ex instanceof HttpMessageNotReadableException) {
			
			
			msg = "Não está sendo enviado dados para o BODY";
			
		}
		
		
		else {

			msg = "Erro" + ex.getMessage();

		}

		objetoErroDTO.setError(msg);

		objetoErroDTO.setCode(+status.value() + " ==> " + status.getReasonPhrase());

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/*
	 * Captura Excecoes a nivel de Banco de Dados (já que pode acontecer de o metodo
	 * acima não capturar)
	 */

	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {

			msg = "Erro de Integridade do Banco: " + ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();

		} else

		if (ex instanceof ConstraintViolationException) {

			msg = "Erro de violação de Chave Estrangeira: " + ((ConstraintViolationException) ex).getCause().getCause().getMessage();

		} else

		if (ex instanceof SQLException) {

			msg = "Erro de SQL do Banco: " + ((SQLException) ex).getCause().getCause().getMessage();

		} else {

			msg = ex.getMessage();

		}
		
		

		objetoErroDTO.setError(msg);

		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	
	}

}
