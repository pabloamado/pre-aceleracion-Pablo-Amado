package com.init.Challenge.exception.msg;

public class ExceptionMsg {

	private final static String DTO_WRONG_DATA=" Los datos del dto no son validos. ";
	
	public final static String USER_PASSWORD_NOT_FOUND="USUARIO O CONTRASEÑA NO ENCONTRADA.";
	
	public final static String ERROR_IN_REGISTER="Error en la validacion del registro.";
	
	public final static String ERROR_TRYING_TO_SAVE="Ha ocurrido un error al intentar "
			+ "guardar la entidad."+  DTO_WRONG_DATA;
	
	public final static String ERROR_TRYING_TO_UPDATE="Ha ocurrido un error al intentar "
			+ "actualizar la entidad." + DTO_WRONG_DATA;
	
	public final static String CHARACTER_NOT_FOUND="Se ha intentado borrar a un personaje, pero no se ha encontrado.";
	
	public final static String GENRE_NOT_FOUND="Se ha intentado borrar un genero, pero no se ha encontrado.";
	
	public final static String MOVIE_NOT_FOUND="Se ha intentado borrar una pelicula, pero no se ha encontrado.";
	
	public final static String SEND_EMAIL_FAIL="El email no pudo ser enviado.";

	public final static String USER_EXISTS="El usuario ya existe.";
}
