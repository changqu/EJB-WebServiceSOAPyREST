using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sdi3_180.Cli_SOAP_CSharp
{
    class ListarUsuarios
    {
        private EjbAdminServiceService adminService = new EjbAdminServiceService();

        public void execute(){
	        user[] users = adminService.getUsuarios();
	        Console.WriteLine("ID - Nombre - Apellidos - Login - Password - Email - Estado - Viaje promovidos - Viajes participado");
	        int viajePromovido = 0;
	        int viajeParticipado = 0;
            string format = "{0,-15} {1, -15} {2, -15} {3, -15} {4, -15} {5, -15} {6, -15} {7, -15} {8, -15}";
	        foreach (user u in users) {
		        viajePromovido = adminService.getViajesPromovido(u);
		        viajeParticipado = adminService.getViajesParticipado(u);
                string line = String.Format(format, 
				        u.id, u.name, u.surname, 
				        u.login, u.password, 
				        u.email, u.status.ToString(),
				        viajePromovido, viajeParticipado);
                Console.WriteLine(line);
	        }	
        }

    }
}
