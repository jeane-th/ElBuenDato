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
                        <p>Cuenta</p>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                             class="lucide lucide-chevron-down-icon lucide-chevron-down">
                        <path d="m6 9 6 6 6-6" />
                        </svg>
                    </button>
                    <div id="menu_cuenta"
                         class="hidden absolute top-14 right-4 py-2 font-semibold bg-slate-900 rounded-lg z-40">
                        <ul class="flex flex-col items-end">
                            <li><a href="" class="block px-6 py-2 hover:text-violet-400">Iniciar sesión</a></li>
                            <li><a href="" class="block px-6 py-2 hover:text-violet-400">Crear cuenta</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
        <main class="relative">
            <!-- Tabla de datos -->
            <div class="max-w-7xl mx-auto py-10 px-4 flex flex-col items-center">
                <div class="w-full m-auto bg-slate-900 rounded-xl">
                    <!-- Menu administrador -->
                    <div class="border-b-1 border-slate-500 px-6">
                        <p class="text-2xl font-bold py-4 pb-6">Panel de administrador</p>
                        <ul class="flex">
                            <li
                                class="font-bold py-2 px-4 cursor-pointer border-1 border-transparent bg-violet-400 rounded-t-lg hover:border-violet-400 hover:border-b-1">
                                Locales</li>
                            <li
                                class="font-bold py-2 px-4 cursor-pointer border-1 border-transparent hover:border-b-violet-400 hover:border-b-1">
                                Usuarios</li>
                            <li
                                class="font-bold py-2 px-4 cursor-pointer border-1 border-transparent hover:border-b-violet-400 hover:border-b-1">
                                Comentarios</li>
                            <li
                                class="font-bold py-2 px-4 cursor-pointer border-1 border-transparent hover:border-b-violet-400 hover:border-b-1">
                                Locales</li>
                        </ul>
                    </div>
                    <!-- Buscador y filtros -->
                    <div class="flex justify-between m-auto p-6">
                        <!-- Budcador -->
                        <div class="relative w-full max-w-lg">
                            <div class="overflow-hidden w-full">
                                <div class="bg-slate-800 rounded-xl border-slate-500 border-1 p-2">
                                    <label for="search" class="hidden">Buscar</label>
                                    <div class="flex items-center justify-between">
                                        <div class="px-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                 viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                 stroke-linecap="round" stroke-linejoin="round"
                                                 class="lucide lucide-search-icon lucide-search text-slate-400">
                                            <path d="m21 21-4.34-4.34" />
                                            <circle cx="11" cy="11" r="8" />
                                            </svg>
                                        </div>
                                        <input type="search" name="" id="search" placeholder="Buscar..." class="w-full">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Botones -->
                        <div>
                            <button type="button" id="btnAgregar"
                                    class="bg-violet-400 px-4 py-2 rounded-lg font-semibold cursor-pointer flex items-center gap-2">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="lucide lucide-plus-icon lucide-plus">
                                <path d="M5 12h14" />
                                <path d="M12 5v14" />
                                </svg>
                                Agregar
                            </button>
                        </div>
                    </div>
                    <!-- Tabla -->
                    <table class="w-full">
                        <thead class="bg-slate-800">
                            <tr class="">
                                <th class="py-2 px-6 text-left text-slate-400">Local</th>
                                <th class="py-2 px-6 text-left text-slate-400">Dirección</th>
                                <th class="py-2 px-6 text-left text-slate-400">Web</th>
                                <th class="py-2 px-6 text-left text-slate-400">Distrito</th>
                                <th class="py-2 px-6 text-left text-slate-400">Categoria</th>
                                <th class="py-2 px-6 text-left text-slate-400">Especialidad</th>
                                <th class="py-2 px-6 text-left text-slate-400">Registro</th>
                                <th class="py-2 px-6 text-left text-slate-400"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="l" items="${locales}">
                                <tr class="border-slate-500 border-b-1">
                                    <th class="py-2 px-6 text-left">${l.nombre}</th>
                                    <td class="py-2 px-6 text-left text-slate-400">${l.direccion}</td>
                                    <td class="py-2 px-6 text-left text-slate-400">
                                        <a href="${l.pagina_web}" target="_blank" class="m-auto">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1"
                                                 stroke-linecap="round" stroke-linejoin="round"
                                                 class="lucide lucide-paperclip-icon lucide-paperclip">
                                            <path
                                                d="m16 6-8.414 8.586a2 2 0 0 0 2.829 2.829l8.414-8.586a4 4 0 1 0-5.657-5.657l-8.379 8.551a6 6 0 1 0 8.485 8.485l8.379-8.551" />
                                            </svg>
                                        </a>
                                    </td>
                                    <td class="py-2 px-6 text-left text-slate-400">${l.distrito}</td>
                                    <td class="py-2 px-6 text-left text-slate-400">
                                        <div class="flex flex-col gap-1.5 items-start">
                                            <c:forEach var="cat" items="${l.getListaCategorias()}">
                                                <span
                                                    class="border border-slate-500 rounded-xl px-2 py-0.5 text-xs whitespace-nowrap">
                                                    ${cat}
                                                </span>
                                            </c:forEach>
                                        </div>
                                    </td>

                                    <td class="py-2 px-6 text-left text-slate-400">${l.especialidad}</td>
                                    <td class="py-2 px-6 text-left text-slate-400 whitespace-nowrap">
                                        ${l.getFechaSoloFecha()}</td>
                                    <td class="py-2 px-6 text-left text-slate-400">
                                        <button class="cursor-pointer flex items-center">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                 viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                                 stroke-linecap="round" stroke-linejoin="round"
                                                 class="lucide lucide-ellipsis-icon lucide-ellipsis">
                                            <circle cx="12" cy="12" r="1" />
                                            <circle cx="19" cy="12" r="1" />
                                            <circle cx="5" cy="12" r="1" />
                                            </svg>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- Pie de tabla -->
                    <div class="px-6 py-6 text-slate-400 flex items-center justify-between">
                        <p>Mostrando 1-10 de 1000</p>
                        <div class="flex items-center">
                            <div class="border-slate-500 border-1 px-2 h-8 flex items-center rounded-l-lg"><svg
                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                    fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                    stroke-linejoin="round" class="lucide lucide-chevron-left-icon lucide-chevron-left">
                                <path d="m15 18-6-6 6-6" />
                                </svg></div>
                            <p class="border-slate-500 border-1 px-2 h-8 flex items-center">1</p>
                            <p class="border-slate-500 border-1 px-2 h-8 flex items-center">2</p>
                            <p class="border-slate-500 border-1 px-2 h-8 flex items-center">3</p>
                            <p class="border-slate-500 border-1 px-2 h-8 flex items-center">...</p>
                            <p class="border-slate-500 border-1 px-2 h-8 flex items-center">100</p>
                            <div class="border-slate-500 border-1 px-2 h-8 flex items-center rounded-r-lg">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="lucide lucide-chevron-right-icon lucide-chevron-right">
                                <path d="m9 18 6-6-6-6" />
                                </svg>
                            </div>f
                        </div>
                    </div>
                </div>
            </div>
            <!-- Formulario agregar -->
            <div id="formNuevoLocal"
                 class="hidden bg-slate-950/90 absolute top-1/2 -translate-y-1/2 right-1/2 translate-x-1/2 w-full h-full pt-36">
                <form action="CrudLocalesServlet" method="POST" class="bg-slate-900 text-white max-w-xl p-6 m-auto border-1 border-slate-500 rounded-xl">
                    <!-- Header modal -->
                    <div class="flex justify-between border-b border-slate-500 pb-4">
                        <h2 class="font-bold text-lg">Agregar Local</h2>
                        <button id="closeFormAgregar" type="button" class="hover:bg-slate-950 cursor-pointer text-slate-400 rounded-sm">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                 class="lucide lucide-x-icon lucide-x">
                            <path d="M18 6 6 18" />
                            <path d="m6 6 12 12" />
                            </svg>
                        </button>
                    </div>
                    <!-- Body modal -->
                    <div class=" flex flex-col gap-5">
                        <div class="w-full grid grid-cols-2 gap-5 gap-5 mt-8 mb-6">
                            <div class="flex flex-col gap-2">
                                <label for="nombre" class="font-semibold">Nombre</label>
                                <input type="text"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="Nombre del local" name="nombre">
                            </div>
                            <div class="flex flex-col gap-2">
                                <label for="direccion" class="font-semibold">Direccción</label>
                                <input type="text"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="Direccion" name="direccion">
                            </div>

                            <div class="flex flex-col gap-2">
                                <label for="id_distrito" class="font-semibold">Distrito</label>
                                <div class="relative">
                                    <select name="id_distrito"
                                            class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2 appearance-none w-full">
                                        <option class="hidden" value="" disabled selected>Seleciona un distrito</option>
                                        <c:forEach var="dist" items="${distritos}">
                                            <option value="${dist.id_distrito}">${dist.nombre_distrito}</option>
                                        </c:forEach>
                                    </select>
                                    <!-- La flecha se posiciona encima del select -->
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                         fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                         stroke-linejoin="round"
                                         class="lucide lucide-chevron-down-icon lucide-chevron-down absolute top-1/2 -translate-y-1/2 right-3 text-gray-400">
                                    <path d="m6 9 6 6 6-6" />
                                    </svg>
                                </div>

                            </div>
                            <div class="flex flex-col gap-2">
                                <label for="pagina_web" class="font-semibold">Web</label>
                                <input type="text"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="Url de pagina web" name="pagina_web">
                            </div>
                            <div class="flex flex-col gap-2">
                                <label for="categorias" class="font-semibold">Categoría</label>
                                <div class="grid grid-cols-2">
                                    <label><input type="checkbox" name="categorias" value="1" class=""> Restaurante</label>
                                    <label><input type="checkbox" name="categorias" value="2"> Cafeteria</label>
                                    <label><input type="checkbox" name="categorias" value="3"> Bar</label>
                                </div>


                            </div>
                            <div class="flex flex-col gap-2">
                                <label for="especialidad" class="font-semibold">Especialidad</label>
                                <input type="text"
                                       class="bg-slate-800 rounded-xl border-1 border-slate-500 placeholder-gray-400 p-2"
                                       placeholder="Describa la especialidad" name="especialidad">
                            </div>
                        </div>

                        <button type="submit"
                                class="flex items-center self-start gap-2 bg-violet-400 pl-2 pr-4 py-2 font-semibold rounded-xl cursor-pointer w-auto">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                 stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                                 class="lucide lucide-plus-icon lucide-plus">
                            <path d="M5 12h14" />
                            <path d="M12 5v14" />
                            </svg>
                            Agregar nuevo local
                        </button>
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
    <script src="./app.js"></script>
    <script>
        const btnAgrear = document.getElementById("btnAgregar");
        const closeFormAgregar = document.getElementById("closeFormAgregar");
        btnAgrear.addEventListener("click", function () {
            const formAgregar = document.getElementById("formNuevoLocal");
            formAgregar.classList.remove("hidden");
        });
        closeFormAgregar.addEventListener("click", function () {
            const formAgregar = document.getElementById("formNuevoLocal");
            formAgregar.classList.add("hidden");
        });
    </script>

</html>