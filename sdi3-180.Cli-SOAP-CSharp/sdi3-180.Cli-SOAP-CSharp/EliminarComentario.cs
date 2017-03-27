using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_180.Cli_SOAP_CSharp
{
    class EliminarComentario
    {
        private EjbAdminServiceService adminService = new EjbAdminServiceService();
        private rating rating = null;

        public void execute(long id) {
		    comprobarId(id);
		    if (rating == null){ 
			    Console.WriteLine("Comentario inexistente, no puede eliminar");
			    return;
		    }
            adminService.eliminarComentario(id, true);
            Console.WriteLine("Comentario eliminado correctamente");
	    }

        public void comprobarId(long id){
		    foreach(rating r in adminService.getComentarios()){
			    if(id.Equals(r.id))
				    rating=r;
		    }
	    }
    }
}
