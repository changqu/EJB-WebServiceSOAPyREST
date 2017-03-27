using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_180.Cli_SOAP_CSharp
{
    class DeshabilitarUsuario
    {
        private EjbAdminServiceService adminService = new EjbAdminServiceService();
        private user user = null;


        public void execute(long id) {
		comprobarId(id);
		if (user == null){
			Console.WriteLine("Usuario inexistente, no puede deshabilitarle");
			return;
		}
		if (user.status == userStatus.CANCELLED){ 
			Console.WriteLine("El usuario ya ha sido deshabilitado");
			return;
		}
		adminService.deshabilitarUsuario(id, true);
		Console.WriteLine("Usuario deshabilitado corectamente");
	}

        public void comprobarId(long id){
            foreach (user u in adminService.getUsuarios()){
			    if(id.Equals(u.id))
				    user=u;
		    }
	    }
    }
}
