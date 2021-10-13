package com.init.Challenge.exception.msg;

public class ExceptionHandlerMsg {

	public static final String NO_ENTITY="No se ha encontrado la entidad referida: ";
	
	public static final String ACCESS_NULL_OBJECT="Se intento acceder a una propiedad de un valor nulo: ";
	
	public static final String DB_MSG_CONSTRAIN=
	"Sucedio una excepción de violación de restriccion de la base de datos,"
	+ " se intento eliminar una entidad que se encuentra ligada a otra, "
	+ "o se intento relacionar una entidad a una que no existe: ";
	
	public static final String GENRE_ERROR="Ha ocurrido una excepcion en el servicio del genero: ";
	
	public static final String MOVIE_ERROR="Ha ocurrido una excepcion en el servicio de pelicula: ";
	
	public static final String CHARACTER_ERROR ="Ha ocurrido un error en el servicio del personaje: ";
	
	public static final String REGISTER_ERROR="Ha ocurrido un error en el registro: ";
	
}
