using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_180.Cli_SOAP_CSharp
{
    class UserInterface
    {
	    public void run() {
		    comandos();
		    do {
			    opciones();
			    if (getNumber() == 0){
				    Console.WriteLine("\nHasta pronto!");
				    return;
			    }
                else if (getNumber() == 1)
				    new ListarUsuarios().execute();
                else if (getNumber() == 2)
				    opcionDeshabilitarUsuario();
                else if (getNumber() == 3)
				    new ListarComentarios().execute();
                else if (getNumber() == 4)
				    opcionEliminarComentario();
		    } while (true);
	    }
	
	    public void comandos(){
		    Console.WriteLine("Comandos:");
		    Console.WriteLine("0, 1, 2, 3, 4");
	    }

        public void opciones()
        {
            Console.WriteLine("\nOpción: ");
            Console.WriteLine("0.Salir");
            Console.WriteLine("1.Listar usuarios del sistema");
            Console.WriteLine("2.Deshabilitar un usuario del sistema");
            Console.WriteLine("3.Listar comentarios y puntuaciones");
            Console.WriteLine("4.Eliminar comentarios y puntuaciones");
        }
	
	    public void opcionDeshabilitarUsuario(){
		    do{
			    Console.WriteLine("\nIntroducir \"0\" para salir de esta opcion");
                Console.WriteLine("Intriducir ID del usuario: ");
			    if(getNumber() == 0)
				    break;
                new DeshabilitarUsuario().execute(getId());
		    }while(true);	
	    }
	
	    public void opcionEliminarComentario(){
		    do{
			    Console.WriteLine("\nIntroducir \"0\" para salir de esta opcion");
			    Console.WriteLine("Intriducir ID del comentario: ");
                if (getNumber() == 0)
				    break;
                new EliminarComentario().execute(getId());
		    }while(true);
	    }

        public int getNumber()
        {
            try
            {
                int number = int.Parse(Console.ReadLine());
                return number;
            }
            catch (Exception)
            {
                Console.WriteLine("Comando desconocido -_-");
                return -1;
            }
        }

        public long getId()
        {
            try
            {
                long number = long.Parse(Console.ReadLine());
                return number;
            }
            catch (Exception)
            {
                Console.WriteLine("Id invalido, tiene que ser numeros");
                return -1;
            }

        }

    }
}
