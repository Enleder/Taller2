package Logic;

public class Mensajes {

	public static final String M_PARTICIPANTES_NOM_ART_CORTO = "El Nombre artistico debe contener mas de 5 caracteres";
	public static final String M_PARTICIPANTES_NOM_ART_LARGO = "El Nombre artistico debe contener hasta 80 caracteres";
	public static final String M_PARTICIPANTES_NOM_ART_VACIO = "El Nombre artistico debe contenes mas de 5 caracteres";
	public static final String M_PARTICIPANTES_YAEXISTE = "Error: El participante que desea ingresar ya existe en el sistema";
	public static final String M_PARTICIPANTES_NOHAYNINGUNO = "Error: No hay ningun participante en el sistema";
	public static final String M_PARTICIPANTES_NOHAYMAS = "Error: No hay mas participante en el sistema";
	public static final String M_PARTICIPANTES_NOEXISTE = "Error: El participante que desea listar no existe en el sistema";
	public static final String M_PARTICIPANTES_NOEXISTE_R4 = "Error: El participante al que desea listar performances no existe en el sistema";
	public static final String M_PARTICIPANTES_NOEXISTE_R6 = "Error: El participante al que desea agregar performance no existe en el sistema";

	public static final String M_FINALISTAS_EL_ID_NOES_FINALISTA = "Error: El participante que desea votar no es finalista";

	public static final String M_PARTICIPANTES_FALTANPERFORMANCES = "Error: Al participante le faltan performances por completar";
	public static final String M_PARTICIPANTES_TOPEDEPERFORMANCES = "Error: El participante ya tiene todas las performances";
	public static final String M_PARTICIPANTES_NO_HAY_SUFICIENTES_PARTICIPANTES = "Error: No hay suficientes participantes para definir a los finalistas";

	public static final String M_PERSISTENCIA_RESPALDAR = "Error al respaldar Archivo";
	public static final String M_PERSISTENCIA_RECUPERAR = "Error al recuperar Archivo";
	public static final String M_PERSISTENCIA_RECUPERAR_EXITO = "Archivo recuperado satisfatoriamente";
	public static final String M_PERSISTENCIA_RESPALDAR_EXITO = "Archivo respaldado correctamente";

	public static final String M_FACHADALOGICA_LECTURA_ERROR = "Error al intentar leer datos";
	public static final String M_FACHADALOGICA_ESCRITURA_ERROR = "Error al intentar guardar datos";
	public static final String M_FACHADALOGICA_INTERNAL_ERROR = "Error interno, contacte con un administrador";
	public static final String M_FACHADALOGICA_VOTACION_INACTIVA = "Error, no se pudo acreditar voto, la votacion esta cerrada";
	public static final String M_FACHADALOGICA_ESTADOINACTIVO_ERROR = "Error, la votacion esta cerrada";

	public static final String M_FACHADALOGICA_INDET_FINALISTAS_ERROR = "Error, aun no se han determinado todos los finalistas";
	public static final String M_FACHADALOGICA_RESPALDAR_ERROR = "Error al intentar guardar los datos";
	public static final String M_FACHADALOGICA_RECUPERAR_ERROR = "Error al intentar leer datos";

	public static final String M_PERFORMANCE_NOHAYNINGUNA = "Error: No hay performances para listar";

	/* PERTENECIENTES A SWING FROM */

	public static final String M_PARTICIPANTES_EXITO = "Participante Igresado Satisfactoriamente";
	public static final String M_REMOTE_EXITO = "Conexión realizada con éxito";
	public static final String M_REMOTE_ERROR = "ERROR al intentar Conexión, debe iniciar el servidor remoto, o no se encuentra dicho servidor";

	public static final String M_PARTICIPANTE_ERROR = "ERROR al intentar Cargar un Participante";
	public static final String M_PERFORMANCE_ERROR = "ERROR al intentar Cargar una Performance";
	public static final String M_DESCONOCIDO_ERROR = "ERROR desconocido";

	public static final String M_VotacionAbiertaOHayGanador = "No se puede ejecutar esta opcion ya que la etapa de inscripcion y realizacion de performances termino (la votacion del publico esta abierta o ya hay un ganador)";

	public static final String M_VotacionAbiertaOHayGanadorR5 = "No se puede ejecutar esta opcion ya que la etapa realizacion de performances ya fue cerrada (la votacion del publico esta abierta o ya hay un ganador)";

	public static final String M_AunQuedanPerformances = "No se puede ejecutar esta opcion ya que hay participantes que no compeltaron la totalidad de sus performances";

	public static final String M_VotacionNoAbierta = "No se puede ejecutar esta opcion ya que la etapa de votación no esta abierta";

	public static final String M_NoHaySuficientesParticipantesRegistrados = "No se puede ejecutar esta opcion ya que no hay suficientes participantes registrados";

	public static final String M_ProcedimientoCorrectoR5 = "Se cerro la etapa de performances, se definio a los finalistas y se abrio la votacion al publico exitosamente";

	public static final String M_ProcedimientoCorrectoR7 = "Se cerro la votacion del publico y se definio el ganador exitosamente";

	public static final String M_ErrorAlIntentarConectarServer = "En este momento la aplicacion no esta disponible, reintente mas tarde o contacte con el administrador";

	public static final String M_ErrorNoSeLePuedeAgregarPerformance = "No se puede agregar una nueva performance, este participante ya tiene el maximo de performances permitidas";

	public static final String M_ProcedimientoCorrectoR6 = "Se agrego la performance exitosamente";

}