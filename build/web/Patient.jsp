<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Patient" %>
<%@ page import="manager.PatientManager" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html
  lang="en"
  class="light-style layout-menu-fixed"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="./sneat/assets/"
  data-template="vertical-menu-template-free"
>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>GVCM | Gestion des Visites dans un Centre Médical</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="./sneat/assets/img/favicon/favicon.ico" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="./sneat/assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="./sneat/assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="./sneat/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="./sneat/assets/css/demo.css" />
    <link rel="stylesheet" href="./bootstrap-table.css">

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="./sneat/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="./sneat/assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="./sneat/assets/js/config.js"></script>

    <!-- sweat alert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  </head>

  <body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->

        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
          <div class="app-brand demo">
            <a href="index.jsp" class="app-brand-link">
              <span class="app-brand-logo demo">
                <svg
                  width="25"
                  viewBox="0 0 25 42"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  xmlns:xlink="http://www.w3.org/1999/xlink"
                >
                  <defs>
                    <path
                      d="M13.7918663,0.358365126 L3.39788168,7.44174259 C0.566865006,9.69408886 -0.379795268,12.4788597 0.557900856,15.7960551 C0.68998853,16.2305145 1.09562888,17.7872135 3.12357076,19.2293357 C3.8146334,19.7207684 5.32369333,20.3834223 7.65075054,21.2172976 L7.59773219,21.2525164 L2.63468769,24.5493413 C0.445452254,26.3002124 0.0884951797,28.5083815 1.56381646,31.1738486 C2.83770406,32.8170431 5.20850219,33.2640127 7.09180128,32.5391577 C8.347334,32.0559211 11.4559176,30.0011079 16.4175519,26.3747182 C18.0338572,24.4997857 18.6973423,22.4544883 18.4080071,20.2388261 C17.963753,17.5346866 16.1776345,15.5799961 13.0496516,14.3747546 L10.9194936,13.4715819 L18.6192054,7.984237 L13.7918663,0.358365126 Z"
                      id="path-1"
                    ></path>
                    <path
                      d="M5.47320593,6.00457225 C4.05321814,8.216144 4.36334763,10.0722806 6.40359441,11.5729822 C8.61520715,12.571656 10.0999176,13.2171421 10.8577257,13.5094407 L15.5088241,14.433041 L18.6192054,7.984237 C15.5364148,3.11535317 13.9273018,0.573395879 13.7918663,0.358365126 C13.5790555,0.511491653 10.8061687,2.3935607 5.47320593,6.00457225 Z"
                      id="path-3"
                    ></path>
                    <path
                      d="M7.50063644,21.2294429 L12.3234468,23.3159332 C14.1688022,24.7579751 14.397098,26.4880487 13.008334,28.506154 C11.6195701,30.5242593 10.3099883,31.790241 9.07958868,32.3040991 C5.78142938,33.4346997 4.13234973,34 4.13234973,34 C4.13234973,34 2.75489982,33.0538207 2.37032616e-14,31.1614621 C-0.55822714,27.8186216 -0.55822714,26.0572515 -4.05231404e-15,25.8773518 C0.83734071,25.6075023 2.77988457,22.8248993 3.3049379,22.52991 C3.65497346,22.3332504 5.05353963,21.8997614 7.50063644,21.2294429 Z"
                      id="path-4"
                    ></path>
                    <path
                      d="M20.6,7.13333333 L25.6,13.8 C26.2627417,14.6836556 26.0836556,15.9372583 25.2,16.6 C24.8538077,16.8596443 24.4327404,17 24,17 L14,17 C12.8954305,17 12,16.1045695 12,15 C12,14.5672596 12.1403557,14.1461923 12.4,13.8 L17.4,7.13333333 C18.0627417,6.24967773 19.3163444,6.07059163 20.2,6.73333333 C20.3516113,6.84704183 20.4862915,6.981722 20.6,7.13333333 Z"
                      id="path-5"
                    ></path>
                  </defs>
                  <g id="g-app-brand" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                    <g id="Brand-Logo" transform="translate(-27.000000, -15.000000)">
                      <g id="Icon" transform="translate(27.000000, 15.000000)">
                        <g id="Mask" transform="translate(0.000000, 8.000000)">
                          <mask id="mask-2" fill="white">
                            <use xlink:href="#path-1"></use>
                          </mask>
                          <use fill="#696cff" xlink:href="#path-1"></use>
                          <g id="Path-3" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-3"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-3"></use>
                          </g>
                          <g id="Path-4" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-4"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-4"></use>
                          </g>
                        </g>
                        <g
                          id="Triangle"
                          transform="translate(19.000000, 11.000000) rotate(-300.000000) translate(-19.000000, -11.000000) "
                        >
                          <use fill="#696cff" xlink:href="#path-5"></use>
                          <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-5"></use>
                        </g>
                      </g>
                    </g>
                  </g>
                </svg>
              </span>
              <span class="app-brand-text demo menu-text fw-bolder ms-2">GVCM</span>
            </a>

            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>

          <div class="menu-inner-shadow"></div>
          <ul class="menu-inner py-1">
            <!-- Medecin -->
            <li class="menu-item ">
              <a href="Medecin" class="menu-link">
                <i class="menu-icon tf-icons bx bx-plus-medical"></i>
                <div data-i18n="Analytics">Medecin</div>
              </a>
            </li>

            <!-- Patient -->
            <li class="menu-item active">
              <a href="Patient" class="menu-link">
                <i class="menu-icon tf-icons bx bx-group"></i>
                <div data-i18n="Basic">Patient</div>
              </a>
            </li>

            <!-- Visite -->
            <li class="menu-item">
              <a href="Visiter" class="menu-link">
                <i class="menu-icon tf-icons bx bx-notepad"></i>
                <div data-i18n="Boxicons">Visite</div>
              </a>
            </li>
          </ul>
        </aside>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">

          <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <div class="row">
                <div class="col-lg-8 mb-4 order-0">
                  <div class="card">
                    <div class="d-flex align-items-end row">
                      <div class="col-sm-7">
                        <div class="card-body">
                          <h5 class="card-title text-primary">Gestion des patients</h5>
                          <p class="mb-4">
                            Vous pouvez cliquer sur le boutton ajouter pour ajouter un patient
                          </p>

                          <div class="mt-3">
                            <!-- Button trigger modal -->
                            <button
                              type="button"
                              class="btn btn-sm btn-outline-primary"
                              data-bs-toggle="modal"
                              data-bs-target="#addPat"
                            >
                              Ajouter
                            </button>
    
                            <!-- Modal -->
                            <div class="modal fade" id="addPat" tabindex="-1" aria-hidden="true">
                            <!-- <form action="/GVCM/Medecin" method="post"> -->

                                <div class="modal-dialog" role="document">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel1">Ajout d'un patient</h5>
                                      <button
                                        type="button"
                                        class="btn-close"
                                        data-bs-dismiss="modal"
                                        aria-label="Close"
                                      ></button>
                                    </div>
                                    <div class="modal-body">
                                      <div class="row">
                                        <div class="col mb-3">
                                          <label for="nameBasic" class="form-label">Nom</label>
                                          <input type="text" id="nom" class="form-control" required placeholder="Nom" />
                                        </div>
                                      </div>
                                      <div class="row">
                                        <div class="col mb-3">
                                          <label for="nameBasic" class="form-label">Prénoms</label>
                                          <input type="text" id="prenom" class="form-control" required placeholder="Prénoms" />
                                        </div>
                                      </div>
                                      <div class="row">
                                        <div class="col mb-3">
                                          <label for="nameBasic" class="form-label">Sexe</label>
                                          <input type="text" id="sexe" class="form-control" required placeholder="Sexe" />
                                        </div>
                                      </div>
                                      <div class="row">
                                        <div class="col mb-3">
                                          <label for="nameBasic" class="form-label">Adresse</label>
                                          <input type="text" id="adresse" class="form-control" required placeholder="Adresse" />
                                        </div>
                                      </div>

                                    </div>
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        Fermer
                                      </button>
                                      
                                      <button class="btn btn-primary" onclick="Myfunc()">Enregistrer</button>
                                    </div>
                                  </div>
                                </div>
                            <!-- </form> -->  
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-sm-5 text-center text-sm-left">
                        <div class="card-body pb-0 px-0 px-md-4">
                          <img
                            src="./sneat/assets/img/illustrations/Patient.png"
                            height="140"
                            alt="View Badge User"
                            data-app-dark-img="illustrations/Patient.png"
                            data-app-light-img="illustrations/Patient.png"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 col-md-4 order-1">
                  <div class="row">
                    <div class="col-lg-6 col-md-12 col-6 mb-4">
                      <div class="card">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                              <img
                                src="./sneat/assets/img/icons/unicons/chart-success.png"
                                alt="chart success"
                                class="rounded"
                              />
                            </div>
                          </div>
                          <span class="fw-semibold d-block mb-1">Total Patients</span>
                          <h3 class="card-title mb-2"><%= (Integer) request.getAttribute("totalPat") %></h3>
                          <small class="text-success fw-semibold"><i class="bx bx-up-arrow-alt"></i></small>
                        </div>
                      </div>
                    </div>
                    <div class="col-lg-6 col-md-12 col-6 mb-4">
                      <div class="card">
                        <div class="card-body">
                          <div class="card-title d-flex align-items-start justify-content-between">
                            <div class="avatar flex-shrink-0">
                              <img
                                src="./sneat/assets/img/icons/unicons/chart.png"
                                alt="chart success"
                                class="rounded"
                              />
                            </div>
                          </div>
                          <span class="fw-semibold d-block mb-1">Patients à visiter</span>
                          <h3 class="card-title mb-2"><%= (Integer) request.getAttribute("patVi") %></h3>
                          <small class="text-success fw-semibold"><i class="bx bx-up-arrow-alt"></i></small>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>             
                
              </div>

              <div class="row">
                <!-- tableau-->
                <div class="col-lg-12 ">
                  <div class="card">
                    <div class="card-datatable text-nowrap">
                      <table class="datatables-basic table table-bordered table-responsive" id="DataTables_Table_0">
                        <thead>
                          <tr>
                            <th>CodePat</th>
                            <th>Nom</th>
                            <th>Prénoms</th>
                            <th>Sexe</th>
                            <th>Adresse</th>
                            <th class="d-flex align-items-center">Action</th>
                          </tr>
                        </thead>
                        <tbody>
                          <% for (Patient pat : (List<Patient>) request.getAttribute("listePatients")) { %>
                            <tr>
                              <td class="num"><%= pat.getCodepat() %></td>
                              <td><%= pat.getNom() %></td>
                              <td><%= pat.getPrenom() %></td>
                              <td><%= pat.getSexe() %></td>
                              <td><%= pat.getAdresse() %></td>
                              <td class="actionB">
                                <div class="demo-inline-spacing">
                                  <button type="button" class="btn btn-icon btn-primary"  data-bs-toggle="modal" data-bs-target="#modal<%= pat.getCodepat() %>" >
                                    <i class="bx bx-edit-alt me-1"></i>
                                  </button>
            
                                    <!-- Modal -->
                                    <div class="modal fade" id="modal<%= pat.getCodepat() %>" tabindex="-1" aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel1">Modification du patient de Codepat <strong><%= pat.getCodepat()%></strong> </h5>
                                            <button
                                              type="button"
                                              class="btn-close"
                                              data-bs-dismiss="modal"
                                              aria-label="Close"
                                            ></button>
                                          </div>
                                          <div class="modal-body">
                                            <div class="row">
                                              <div class="col mb-3">
                                                <label for="nameBasic" class="form-label">Nom</label>
                                                <input type="text" id="nom-<%= pat.getCodepat() %>" class="form-control" value="<%= pat.getNom()%>" />
                                              </div>
                                            </div>
                                            <div class="row">
                                              <div class="col mb-3">
                                                <label for="nameBasic" class="form-label">Prénoms</label>
                                                <input type="text" id="prenom-<%= pat.getCodepat() %>" class="form-control" value="<%= pat.getPrenom()%>" />
                                              </div>
                                            </div>
                                            <div class="row">
                                              <div class="col mb-3">
                                                <label for="nameBasic" class="form-label">Sexe</label>
                                                <input type="text" id="sexe-<%= pat.getCodepat() %>" class="form-control" value="<%= pat.getSexe()%>" />
                                              </div>
                                            </div>
                                            <div class="row">
                                              <div class="col mb-3">
                                                <label for="nameBasic" class="form-label">Adresse</label>
                                                <input type="text" id="adresse-<%= pat.getCodepat() %>" class="form-control" value="<%= pat.getAdresse()%>" />
                                              </div>
                                            </div>
                                          
        
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                              Fermer
                                            </button>
                                            <button type="button" class="btn btn-primary" 
                                              onclick="submitUpdate('<%= pat.getCodepat()%>')"
                                              data-bs-dismiss="modal"
                                            >Enregistrer</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>

                                  <button type="button" class="btn btn-icon btn-secondary" data-bs-toggle="modal" data-bs-target="#modalSup<%= pat.getCodepat() %>">
                                      <!-- <input type="hidden" name="action" value="supprimer">
                                      <input type="hidden" name="idpatToDelete" id="1" value="<%= pat.getCodepat()%>"> -->
                                      <i class="bx bx-trash me-1"></i>
                                  </button>

                                    <div class="modal fade" id="modalSup<%= pat.getCodepat() %>" tabindex="-1" aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h5 class="modal-title" id="modalLongTitle">Suppression d'un patient</h5>
                                            <button
                                              type="button"
                                              class="btn-close"
                                              data-bs-dismiss="modal"
                                              aria-label="Close"
                                            ></button>
                                          </div>
                                          <div class="modal-body">
                                            <p>
                                              Voulez vous vraiment supprimer le patient <strong><%= pat.getCodepat()%></strong>?
                                            </p>
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-secondary" onclick="delet('<%= pat.getCodepat()%>')" data-bs-dismiss="modal">
                                              Oui
                                            </button>
                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Non</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
  
                                </div>
                              </td>
                            </tr>
                          
                          <% } %>
  
                        </tbody>
                      </table>
                  </div>
                </div>
                </div>
              </div>
            </div>
            <!-- / Content -->


            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->


    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="./sneat/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="./sneat/assets/vendor/libs/popper/popper.js"></script>
    <script src="./sneat/assets/vendor/js/bootstrap.js"></script>
    <script src="./sneat/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="./sneat/assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="./sneat/assets/vendor/libs/masonry/masonry.js"></script>

    <!-- Main JS -->
    <script src="./sneat/assets/js/main.js"></script>

    <!-- Page JS -->

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <script src="./bootstrap-tab.js"></script>
    <script src="./bootstrap-table.js"></script>
    <script>

      function Myfunc(){
        let nom = $("#nom").val();
        let prenom = $("#prenom").val();
        let sexe = $("#sexe").val();
        let adresse = $("#adresse").val();
        let maforme = {
          nom : nom,
          prenom : prenom,
          sexe : sexe,
          adresse : adresse,
        };
        
        $.ajax({
          type:"POST",
          url:'/GVCM/Patient',
          data : maforme,
          dataType: "json",
          success: function (response) {
              $("#addPat").modal("hide");  
              console.log("Réponse reçue : ", response);

              if (response && response.status === "success") {
                  Swal.fire({
                      title: "Succès !",
                      text: response.message,
                      icon: "success",
                      confirmButtonText: "OK"
                  }).then((result) => {
                      if (result.isConfirmed) {
                          location.reload();  // recharge la page
                      }
                  });
              } else {
                Swal.fire({
                  title: "Erreur !",
                  text: response.message,
                  icon: "error"
                });
                  console.log("❌ Erreur ou réponse inattendue");
              }
          }
        });

        // if (nom && prenom && sexe && adresse  ) {
        //   console.log(nom);
        // } else {
        //   console.log("fenoy!");
        // }
        
      }

      function clearImp(){
        $(".form input").val("");
      }

      function update(codepat,nom,prenom,sexe,adresse) {
        let maforme = {
          codepat : codepat,
          nom : nom,
          prenom : prenom,
          sexe : sexe,
          adresse : adresse,
        };

        $.ajax({
          type:"POST",
          url:'/GVCM/Patient',
          data : maforme,
          dataType: "json",
          success: function (response) {
              $("#addPat").modal("hide");  
              console.log("Réponse reçue : ", response);
              console.log(maforme);
              

              if (response && response.status === "success") {
                  Swal.fire({
                      title: "Succès !",
                      text: response.message,
                      icon: "success",
                      confirmButtonText: "OK"
                  }).then((result) => {
                      if (result.isConfirmed) {
                          location.reload();  // recharge la page
                      }
                  });
              } else {
                Swal.fire({
                  title: "Erreur !",
                  text: response.message,
                  icon: "error"
                });
                  console.log("❌ Erreur ou réponse inattendue");
              }
          }
        });
        

        
      }

      function submitUpdate(codepat) {
        const nom = document.getElementById("nom-" + codepat).value;
        const prenom = document.getElementById("prenom-" + codepat).value;
        const sexe = document.getElementById("sexe-" + codepat).value;
        const adresse = document.getElementById("adresse-" + codepat).value;

        update(codepat, nom, prenom, sexe, adresse);
      }

      function delet(codepat){
        console.log(codepat);
        let maforme = {
          codepat : codepat,
          action : "supprimer"
        };

        $.ajax({
          type:"POST",
          url:'/GVCM/Patient',
          data : maforme,
          dataType: "json",
          success: function (response) {
              $("#addPat").modal("hide");  
              console.log("Réponse reçue : ", response);
              

              if (response && response.status === "success") {
                  Swal.fire({
                      title: "Succès !",
                      text: response.message,
                      icon: "success",
                      confirmButtonText: "OK"
                  }).then((result) => {
                      if (result.isConfirmed) {
                          location.reload();  // recharge la page
                      }
                  });
              } else {
                Swal.fire({
                  title: "Erreur !",
                  text: response.message,
                  icon: "error"
                });
                  console.log("❌ Erreur ou réponse inattendue");
              }
          }
        });
        
      }
    </script>
  </body>
  <script>
    new DataTable('#DataTables_Table_0', {
        language: {
          url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/fr-FR.json'
        }
    });
  </script>
</html>
