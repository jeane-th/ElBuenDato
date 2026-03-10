<%-- 
        Document   : index
        Created on : 7 feb 2026, 21:49:08
        Author     : jtafu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Document</title>
        <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

    </head>

    <body class="bg-slate-950 mx-auto text-white">
        <header class="border-b border-slate-800">
            <div class="mx-auto py-4 px-4 h-14 flex items-center justify-between relative max-w-7xl">
                <div class="flex items-end">
                    <div class="text-xl font-bold mr-14 cursor-pointer">
                        ElBuenDato
                    </div>
                    <ul class="flex items-end font-semibold gap-2">
                        <li><a class="hover:text-violet-400 px-4 py-2" href="#">Inicio</a></li>
                        <li><a class="hover:text-violet-400 px-4 py-2" href="#">Restaurantes</a></li>
                        <li><a class="hover:text-violet-400 px-4 py-2" href="#">Cafeterias</a></li>
                        <li><a class="hover:text-violet-400 px-4 py-2" href="#">Bares</a></li>
                    </ul>
                </div>
                <div>
                    <button id="button_user" type="button"
                            class="flex font-semibold gap-2 items-end cursor-pointer bg-violet-400 px-4 py-2 rounded-lg">
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                             class="lucide lucide-user-icon lucide-user">
                        <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2" />
                        <circle cx="12" cy="7" r="4" />
                        </svg>
                        <p>Inicia sesión</p>
                    </button>
                </div>
            </div>
        </header>
        <main class="relative">
            <!-- Formulario agregar -->
            <div id="formNuevoLocal" class="bg-slate-950/90 w-full h-full py-20">
                <form id="formRegistro" action="UsuarioServlet" method="POST"
                      class="bg-slate-900 text-white max-w-sm p-6 m-auto border-1 border-slate-500 rounded-xl">
                    <input type="hidden" name="accion" value="registrar">
                    <!-- Header modal -->
                    <div class="border-b border-slate-500 pb-4 text-center">
                        <h2 class="font-bold text-lg">Registro</h2>
                    </div>
                    <!-- Body modal -->
                    <div class=" flex flex-col gap-5">
                        <div class="w-full grid grid-cols-1 gap-5 gap-5 mt-8 mb-6">
                            <div class="flex flex-col gap-2">
                                <label for="nombre" class="font-semibold">Nombre *</label>
                                <input type="text" id="nombre"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="Nombre" name="nombre">
                            </div>
                            <div class="flex flex-col gap-2">
                                <label for="apellido" class="font-semibold">Apellido</label>
                                <input type="text" id="Apellido"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="Apellido" name="apellido">
                            </div>

                            <div class="flex flex-col gap-2">
                                <label for="correo" class="font-semibold">Correo *</label>
                                <input type="email" id="email"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="usuario@correo.com" name="correo">
                            </div>

                            <div class="flex flex-col gap-2">
                                <label for="password" class="font-semibold">Contraseña *</label>
                                <input type="password" id="password"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="************" name="password">
                            </div>
                        </div>

                        <button type="submit"
                                class="gap-2 bg-violet-400 pl-4 pr-4 py-2 font-semibold rounded-xl cursor-pointer w-auto text-center">
                            Registarse
                        </button>
                    </div>
                    <div class="text-red-200 my-3 text-center max-w-sm mx-auto">
                        <c:if test="${not empty error}">
                            <p>${error}</p>
                        </c:if>
                    </div>
                </form>
            </div>

        </main>

        <footer class="border-t border-slate-800">
            <div class="max-w-7xl mx-auto py-4 px-4 flex flex-col items-center">
                <div class="flex items-center justify-between w-full">
                    <div class="text-2xl font-bold mr-14 cursor-pointer">
                        ElBuenDato
                    </div>
                    <ul class="flex gap-6">
                        <li><a class="hover:underline hover:underline-offset-1" href="#">About</a> </li>
                        <li><a class="hover:underline hover:underline-offset-1" href="#">Privacidad</a></li>
                        <li><a class="hover:underline hover:underline-offset-1" href="#">Contacto</a></li>
                    </ul>
                </div>
                <!-- <div class="my-4 w-1/2 h-px bg-slate-800"></div> -->
                <div class="mb-4 my-4">
                    <p>© 2026 Todos los derechos reservados</p>
                </div>
            </div>

        </footer>
    </body>

    <script src="https://cdn.jsdelivr.net/npm/just-validate@latest/dist/just-validate.production.min.js"></script>
    <script>
        const validate = new JustValidate('#formRegistro', {
            errorFieldCssClass: 'border-red-200',
            errorLabelCssClass: 'text-red-200 text-sm mt-1',
            focusInvalidField: true
        });

        validate
                .addField('#nombre', [
                    {rule: 'required', errorMessage: 'Complete su nombre'},
                    {rule: 'minLength', value: 3, errorMessage: 'Mínimo 3 caracteres'}
                ])
                .addField('#email', [// He usado #email porque así lo tenías en el ID del HTML
                    {rule: 'required', errorMessage: 'Complete su email'},
                    {rule: 'email', errorMessage: 'Email inválido'}
                ])
                .addField('#password', [
                    {rule: 'required', errorMessage: 'Complete su contraseña'},
                    {rule: 'minLength', value: 6, errorMessage: 'Mínimo 6 caracteres'}
                ]) // AQUÍ QUITÉ EL PUNTO Y COMA
                .onSuccess((event) => {
                    console.log('Validación exitosa, enviando...');
                    event.target.submit();
                });



    </script>

</html>