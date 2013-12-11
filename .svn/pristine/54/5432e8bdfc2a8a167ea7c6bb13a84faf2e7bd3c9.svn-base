/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

    $(".form-user").each(function(i) {
        $(this).attr("id", "form-user" + (i + 1));
        $("#form-user" + (i + 1)).submit(function() {
            ajaxForm('form-user' + (i + 1), '/users/updateuser', $("div.ajaxable-user"));
            return false;
        });
    });

    $(".form-user-admin").each(function(i) {
        $(this).attr("id", "form-user-admin" + (i + 1));
        $("#form-user-admin" + (i + 1)).submit(function() {
            ajaxForm('form-user-admin' + (i + 1), '/administration/updateuser', $("div.ajaxable-user-admin"));
            return false;
        });
    });

    $(".form-adresse").each(function(i) {
        $(this).attr("id", "form-adresse" + (i + 1));
        $("#form-adresse" + (i + 1)).submit(function() {
            ajaxForm('form-adresse' + (i + 1), '/cvs/updateadresse', $("#form-adresse" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-cv").each(function(i) {
        $(this).attr("id", "form-cv" + (i + 1));
        $("#form-cv" + (i + 1)).submit(function() {
            ajaxForm('form-cv' + (i + 1), '/cvs/updatecv', $("#form-cv" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-formation").each(function(i) {
        $(this).attr("id", "form-formation" + (i + 1));
        $("#form-formation" + (i + 1)).submit(function() {
            ajaxForm('form-formation' + (i + 1), '/cvs/updateformation', $("#form-formation" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-experience").each(function(i) {
        $(this).attr("id", "form-experience" + (i + 1));
        $("#form-experience" + (i + 1)).submit(function() {
            ajaxForm('form-experience' + (i + 1), '/cvs/updateexperience', $("#form-experience" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-skill").each(function(i) {
        $(this).attr("id", "form-skill" + (i + 1));
        $("#form-skill" + (i + 1)).submit(function() {
            ajaxForm('form-skill' + (i + 1), '/cvs/updateskill', $("#form-skill" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-hobbie").each(function(i) {
        $(this).attr("id", "form-hobbie" + (i + 1));
        $("#form-hobbie" + (i + 1)).submit(function() {
            ajaxForm('form-hobbie' + (i + 1), '/cvs/updatehobbie', $("#form-hobbie" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-language").each(function(i) {
        $(this).attr("id", "form-language" + (i + 1));
        $("#form-language" + (i + 1)).submit(function() {
            ajaxForm('form-language' + (i + 1), '/cvs/updatelanguage', $("#form-language" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-contact").each(function(i) {
        $(this).attr("id", "form-contact" + (i + 1));
        $("#form-contact" + (i + 1)).submit(function() {
            ajaxForm('form-contact' + (i + 1), '/application/sendfeedback', $("#form-contact" + (i + 1) + "#ajaxable"));
            return false;
        });
    });

    $(".form-search-admin").each(function(i) {
        $(this).attr("id", "form-search-admin" + (i + 1));
        $("#form-search-admin" + (i + 1)).submit(function() {
            ajaxAdminFormSearch('form-search-admin' + (i + 1), '/administration/search');
            return false;
        });
    });

    $("#form-search-admin").live('submit', function() {
        ajaxAdminFormSearch('form-search-admin', '/administration/search');
        return false;
    });

    $(".form-search-annuaire").each(function(i) {
        $(this).attr("id", "form-search-annuaire" + (i + 1));
        $("#form-search-annuaire" + (i + 1)).submit(function() {
            ajaxAnnuaireFormSearch('form-search-annuaire' + (i + 1), '/annuaires/search');
            return false;
        });
    });

    $("#form-search-annuaire").live('submit', function() {
        ajaxAnnuaireFormSearch('form-search-annuaire', '/annuaires/search');
        return false;
    });

    $(".ajax-loader").each(function(i) {
        $(this).hide();
    });

});

function ajaxForm(idForm, url, element) {
    $("#ajax-loader").show();
    $.ajax({
        type: "POST",
        url: url,
        data: $('#' + idForm).serialize(),
        success: function(data) {
//            if (element != 'undefined' && element) {
//                element.html(data);
//            } else {
////                $('#' + idForm).append(data);
//                $("#"+idForm).html(data);
//            }

            $("#" + idForm).html(data);

            $(".form-user").each(function(i) {
                $(this).attr("id", "form-user" + (i + 1));
                $("#form-user" + (i + 1)).submit(function() {
                    ajaxForm('form-user' + (i + 1), '/users/updateuser', $("div.ajaxable-user"));
                    return false;
                });
            });

            $(".form-user-admin").each(function(i) {
                $(this).attr("id", "form-user-admin" + (i + 1));
                $("#form-user-admin" + (i + 1)).submit(function() {
                    ajaxForm('form-user-admin' + (i + 1), '/administration/updateuser', $("div.ajaxable-user-admin"));
                    return false;
                });
            });

            $(".form-adresse").each(function(i) {
                $(this).attr("id", "form-adresse" + (i + 1));
                $("#form-adresse" + (i + 1)).submit(function() {
                    ajaxForm('form-adresse' + (i + 1), '/cvs/updateadresse');
                    return false;
                });
            });

            $(".form-cv").each(function(i) {
                $(this).attr("id", "form-cv" + (i + 1));
                $("#form-cv" + (i + 1)).submit(function() {
                    ajaxForm('form-cv' + (i + 1), '/cvs/updatecv');
                    return false;
                });
            });

            $(".form-formation").each(function(i) {
                $(this).attr("id", "form-formation" + (i + 1));
                $("#form-formation" + (i + 1)).submit(function() {
                    ajaxForm('form-formation' + (i + 1), '/cvs/updateformation');
                    return false;
                });
            });

            $(".form-experience").each(function(i) {
                $(this).attr("id", "form-experience" + (i + 1));
                $("#form-experience" + (i + 1)).submit(function() {
                    ajaxForm('form-experience' + (i + 1), '/cvs/updateexperience');
                    return false;
                });
            });

            $(".form-skill").each(function(i) {
                $(this).attr("id", "form-skill" + (i + 1));
                $("#form-skill" + (i + 1)).submit(function() {
                    ajaxForm('form-skill' + (i + 1), '/cvs/updateskill');
                    return false;
                });
            });

            $(".form-hobbie").each(function(i) {
                $(this).attr("id", "form-hobbie" + (i + 1));
                $("#form-hobbie" + (i + 1)).submit(function() {
                    ajaxForm('form-hobbie' + (i + 1), '/cvs/updatehobbie');
                    return false;
                });
            });

            $(".form-language").each(function(i) {
                $(this).attr("id", "form-language" + (i + 1));
                $("#form-language" + (i + 1)).submit(function() {
                    ajaxForm('form-language' + (i + 1), '/cvs/updatelanguage');
                    return false;
                });
            });

            $(".form-contact").each(function(i) {
                $(this).attr("id", "form-contact" + (i + 1));
                $("#form-contact" + (i + 1)).submit(function() {
                    ajaxForm('form-contact' + (i + 1), '/application/sendfeedback');
                    return false;
                });
            });

            $(".ajax-loader").each(function() {
                $(this).hide();
            });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {// une erreur côté serveur web
            alert(errorThrown);
            $("#ajax-loader").hide();
        }
    });
}

function ajaxAnnuaireFormSearch(idForm, url) {
    $("#ajax-loader").show();
    $.ajax({
        type: "POST",
        url: url,
        // ce qu'on envoie: la valeur qui est dans l'input de l'element avec l'id "nom"
        data: $('#' + idForm).serialize(),
        // si tout se passe bien et on a reçu la réponse du serveur :
        success: function(data) {
            $("div.ajaxable-annuaire").html(data);
            $(".form-search-annuaire").each(function(i) {
                $(this).attr("id", "form-search-annuaire" + (i + 1));
                $("#form-search-annuaire" + (i + 1)).submit(function() {
                    ajaxAnnuaireFormSearch('form-search-annuaire' + (i + 1), '/annuaires/search');
                    return false;
                });
            });
            $("#ajax-loader").hide();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {// une erreur côté serveur web
            $("#waiting").hide(500);
            $("#message").css("background-color", "orange").text("Erreur").show();
            $("#demoForm").show();
            alert(textStatus);
            $("#ajax-loader").hide();
        }
    });
}

function ajaxAdminFormSearch(idForm, url) {
    $("#ajax-loader").show();
    $.ajax({
        type: "POST",
        url: url,
        // ce qu'on envoie: la valeur qui est dans l'input de l'element avec l'id "nom"
        data: $('#' + idForm).serialize(),
        // si tout se passe bien et on a reçu la réponse du serveur :
        success: function(data) {
            $("div.ajaxable-admin").html(data);
            $(".form-search-admin").each(function(i) {
                $(this).attr("id", "form-search-admin" + (i + 1));
                $("#form-search-admin" + (i + 1)).submit(function() {
                    ajaxAdminFormSearch('form-search-admin' + (i + 1), '/administration/search');
                    return false;
                });
            });
            $("#ajax-loader").hide();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {// une erreur côté serveur web
            $("#waiting").hide(500);
            $("#message").css("background-color", "orange").text("Erreur").show();
            $("#demoForm").show();
            alert(textStatus);
            $("#ajax-loader").hide();
        }
    });
}

function participer(id) {
    $("#ajax-loader").show();
    $.ajax({
        type: "POST",
        url: "/events/participer",
        data: {
            id: id
        },
        success: function(data) {
            $("#participe-" + id).text("Je ne vais plus");
            $("#participe-" + id).attr("onclick", "return departiciper(" + id + ");");
            $("#participe-" + id).css("background", "#FF0000");
            $("#ajax-loader").hide();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
            $("#ajax-loader").hide();
        }
    });
    return false;
}

function departiciper(id) {
    $("#ajax-loader").show();
    $.ajax({
        type: "POST",
        url: "/events/departiciper",
        data: {
            id: id
        },
        success: function(data) {
            $("#participe-" + id).text("Participer à cet évènement");
            $("#participe-" + id).attr("onclick", "return participer(" + id + ");");
            $("#participe-" + id).css("background", "#008800");
            $("#ajax-loader").hide();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
            $("#ajax-loader").hide();
        }
    });
    return false;
}

/**
 * 
 * @param {type} control
 * @param {type} action
 * @param {type} id
 * @returns {Boolean}
 */
function subscribe(control, action, id) {
    $("#ajax-loader").show();
    $.ajax({
        type: "POST",
        url: "/" + control + "/" + action + 'subscribe',
        data: {
            id: id
        },
        success: function(data) {
            if (data[0] == 'Success') {
                var tex;
                var act = '';
                var col = "008800";
                if (action == 'un') {
                    tex = 'S\'abonner';
                } else {
                    tex = 'Se désabonner';
                    act = 'un';
                    col = "FF0000";
                }
                $("#subscribe-" + control + "-" + id).text(tex);
                $("#subscribe-" + control + "-" + id).attr("onclick", "return subscribe('" + control + "','" + act + "'," + id + ");");
                $("#subscribe-" + control + "-" + id).css("background", "#" + col);
            }
            $("#ajax-loader").hide();
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
            $("#ajax-loader").hide();
        }
    });
    return false;
}

function message(message, type) {
    var strVar = "";
    strVar += "<div class=\"alert alert-" + type + "\">";
    strVar += "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×<\/button>";
    strVar += "                <strong>" + message + "<\/strong>";
    strVar += "            <\/div>";
    return strVar;
}