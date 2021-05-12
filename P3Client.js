/**
 * This P2 version of JS file is written by Md Ishfaq Alam (A00450249)
 * Functions that exist in the file -
 * setup(), processControl(), calculate(), paraForConcepts(), drawElevation(), drawPlan()
 */

// Constants
const INSULATION = "Insulation";
const WINDOW_MAX_WIDTH = 108;
const WINDOW_MAX_LENGTH = 81;
const SCALE = 1.35; // scale factor
const HALF_TO_INCH = 2; // this is used to create the .5 increments in the Opaque thickness slider values

//variables
var D; // variable which stores the value of Opaque thermal resistance
var H; // variable which stores the value of EffectiveOverallThermalResistanceOutput
var I; // variable which stores the value of KWhAnuualEnergyOutput
var windowAreaTrunc; // variable which stores the truncated window area

/**
 * This function lays out the plan, elevation and their respective contexts so that the functions for drawing the plan and elevation can work
 * This function is intiated when the webpage is loaded
 */
function setup() {
  let plan = document.getElementById("plan");
  let contextP = plan.getContext("2d");

  contextP.clearRect(0, 0, plan.width, plan.height);
  contextP.scale(SCALE, SCALE);

  var elevation = document.getElementById("elevation");
  var contextE = elevation.getContext("2d");

  contextE.clearRect(0, 0, elevation.width, elevation.height);
  contextE.scale(SCALE, SCALE);

  // set initial Opaque Thickness output box value to 2
  $("#planSldOut").val(2);

  // set initial Window area to zero
  $("#windowSldOut").val(0);

  // set Intial door thermal resistance output box value to 2
  $("#doorThermalResistanceSldOut").val(2);

  // set intial window thermal resistance outputbox value to 1
  $("#WindowThermalResistanceSldOut").val(1);

  // set opaque thermal resistance output
  $("#wallOut").val("");

  //set Effective Overall Thermal Resistance Output box to ""
  $("#EffectiveOverallThermalResistanceOutput").val("");

  //set KWhAnuualEnergy output box to ""
  $("#KWhAnuualEnergy").val("");

  // register the wall thickness slider
  $("#planSldOpaqueThickness").val();

  // register the window thickness slider
  $("#windowSld").val();

  //register the door thermal resistance slider
  $("#doorThermalResistanceSld").val();

  //register the window thermal resistance slider
  $("#WindowThermalResistanceSld").val();

  // When the page loads only the row which represents View chapter and the images are visible
  document.getElementById("2ndRow").style.visibility = "hidden";
  document.getElementById("3rdRow").style.visibility = "hidden";
  document.getElementById("4thRow").style.visibility = "hidden";
  document.getElementById("5thRow").style.visibility = "hidden";
  document.getElementById("6thRow").style.visibility = "hidden";
}

/**
 * This function does the changes in the plan and elevation when slider values are changed
 * This function also performs calculations when slider values are changed
 */
function processControl() {
  //slider values
  let OpaqueThicknessConstruction = $("#planSldOpaqueThickness").val();
  let windowThermalResistanceSlider = $("#WindowThermalResistanceSld").val();
  let doorThermalResistanceSlider = $("#doorThermalResistanceSld").val();
  let window = $("#windowSld").val();

  //drop down menus A and B values
  let OpaqueConstructionRValue = $(
    "#OpaqueConstrutionRmenu option:selected"
  ).val();
  let placesWithDegreeDaysValue = $("#placeswithdegreedays").val();

  let plan = document.getElementById("plan");
  let contextP = plan.getContext("2d");

  contextP.clearRect(0, 0, plan.width, plan.height);

  var elevation = document.getElementById("elevation");
  var contextE = elevation.getContext("2d");

  contextE.clearRect(0, 0, elevation.width, elevation.height);

  //Choose the value which represents the options in the view chapter drop down menu
  let choice = $("#control").find(":selected").text();

  if (choice === INSULATION) {
    document.getElementById("2ndRow").style.visibility = "visible";
    document.getElementById("3rdRow").style.visibility = "visible";
    document.getElementById("4thRow").style.visibility = "visible";
    document.getElementById("5thRow").style.visibility = "visible";
    document.getElementById("6thRow").style.visibility = "visible";

    drawPlan();
    drawElevation();
  }

  // Alert message pops up when selection from view chapters dropdown menu is not "INSULATION"
  if (choice != INSULATION && choice != "VIEW CHAPTERS") {
    alert(choice + "is under construction.");
    document.getElementById("2ndRow").style.visibility = "hidden";
    document.getElementById("3rdRow").style.visibility = "hidden";
    document.getElementById("4thRow").style.visibility = "hidden";
    document.getElementById("5thRow").style.visibility = "hidden";
    document.getElementById("6thRow").style.visibility = "hidden";
    location.reload();
  }

  // when view chapters is clicked everything is hidden except first row to make the webpage neat
  if (choice === "VIEW CHAPTERS") {
    document.getElementById("2ndRow").style.visibility = "hidden";
    document.getElementById("3rdRow").style.visibility = "hidden";
    document.getElementById("4thRow").style.visibility = "hidden";
    document.getElementById("5thRow").style.visibility = "hidden";
    document.getElementById("6thRow").style.visibility = "hidden";
    location.reload();
  }

  // Calculates the correct window area necessary for drawing and calculations
  if (window >= 0) {
    let windowArea = (window / 12) * (((window / 12) * 3) / 4);
    windowAreaTrunc = Math.trunc(Number(windowArea) * 10) / 10;
    $("#windowSldOut").val(windowAreaTrunc);
  }

  // Calculate function executed
  calculate(
    OpaqueThicknessConstruction / HALF_TO_INCH,
    OpaqueConstructionRValue,
    windowThermalResistanceSlider,
    doorThermalResistanceSlider,
    placesWithDegreeDaysValue
  );
}

/**
 * This function calculates the Opaque Thermal resistance, EffectiveOverallThermalResistanceOutput and KWhAnuualEnergyOutput values.

  INPUTS - 
  OpaqueThicknessConstruction - Opaque Thickness slider value
  OpaqueConstructionRValue - Opaque construction R value from the drop down menu B
  windowThermalResistanceSlider -  windowThermalResistance Slider value 
  doorThermalResistanceSlider - doorThermalResistance Slider value 
  placesWithDegreeDaysValue - Degree days value from the drop down menu A 
*/

function calculate(
  OpaqueThicknessConstruction,
  OpaqueConstructionRValue,
  windowThermalResistanceSlider,
  doorThermalResistanceSlider,
  placesWithDegreeDaysValue
) {
  // update opaque thickness slider value outputs for display purposes only
  $("#planSldOut").val(OpaqueThicknessConstruction);

  //update window thermal resistance slider value for display purposes only
  $("#WindowThermalResistanceSldOut").val(windowThermalResistanceSlider);

  //update door thermal resistance slider value for display purposes only
  $("#doorThermalResistanceSldOut").val(doorThermalResistanceSlider);

  // opaque thermal resistance output calculation
  let OpaqueThermalResistanceOutput;
  if (OpaqueThicknessConstruction >= 4 && OpaqueConstructionRValue != "top") {
    let K = OpaqueThicknessConstruction - 2;
    let Rvalue = $("#OpaqueConstrutionRmenu option:selected").val();
    D = 2 + K * Rvalue;
    OpaqueThermalResistanceOutput = $("#wallOut").val(D);
  }

  // Effective Overall Thermal Resistance Output calculation
  let EffectiveOverallThermalResistanceOutput;
  if (
    OpaqueThicknessConstruction >= 4 &&
    OpaqueConstructionRValue != "top" &&
    doorThermalResistanceSlider >= 2 &&
    windowThermalResistanceSlider >= 1 &&
    windowAreaTrunc >= 0
  ) {
    H = Math.trunc(
      1 /
        (((800 - windowAreaTrunc) / D +
          windowAreaTrunc / windowThermalResistanceSlider +
          20 / doorThermalResistanceSlider) /
          820)
    );
    EffectiveOverallThermalResistanceOutput = $(
      "#EffectiveOverallThermalResistanceOutput"
    ).val(H);
  }

  // KWhAnuualEnergyOutput calculation
  if (placesWithDegreeDaysValue != "0") {
    let KWhAnuualEnergyOutput;
    I = Math.trunc(
      (820 * placesWithDegreeDaysValue * 43.2) / H / 3412 +
        (placesWithDegreeDaysValue * 2808) / 3412 +
        3000
    );
    KWhAnuualEnergyOutput = $("#KWhAnuualEnergy").val(I);
  }
}

/**
 * This function shows the paragraphs asscoiated with each option in the Concepts drop down menu.
 * When option "concepts" is clicked nothing is showed
 */

function paraForConcepts() {
  var SERVER_URL = "http://ugdev.cs.smu.ca:3400"; // represents the udgev server from where we need to get info from

  //select the values which represent the options from the dropdown menu CONCEPT
  let choice = $("#concept").val();

  // request the server to give the JSON object which represents the required paragraph when an option is selected

  if (choice === "1" && choice != "0") {
    $.get(SERVER_URL + "/malam", function (data) {
      document.getElementById("ConceptsPara").innerHTML = data.LocalConditions;
    }).fail(function (error) {
      alert(error.responseText);
    });
  }

  if (choice === "2" && choice != "0") {
    $.get(SERVER_URL + "/malam", function (data) {
      document.getElementById("ConceptsPara").innerHTML =
        data.AnnualEnergyBudget;
    }).fail(function (error) {
      alert(error.responseText);
    });
  }
  if (choice === "3" && choice != "0") {
    $.get(SERVER_URL + "/malam", function (data) {
      document.getElementById("ConceptsPara").innerHTML =
        data.DraftsAndVentilations;
    }).fail(function (error) {
      alert(error.responseText);
    });
  }
  if (choice === "4" && choice != "0") {
    $.get(SERVER_URL + "/malam", function (data) {
      document.getElementById("ConceptsPara").innerHTML =
        data.InsulationAndHeatLoss;
    }).fail(function (error) {
      alert(error.responseText);
    });
  }
  if (choice === "5" && choice != "0") {
    $.get(SERVER_URL + "/malam", function (data) {
      document.getElementById("ConceptsPara").innerHTML =
        data.MaterialsAndInsulation;
    }).fail(function (error) {
      alert(error.responseText);
    });
  }
  if (choice === "6" && choice != "0") {
    $.get(SERVER_URL + "/malam", function (data) {
      document.getElementById("ConceptsPara").innerHTML =
        data.EnviromentalImpacts;
    }).fail(function (error) {
      alert(error.responseText);
    });
  }
}

/**
 * This function draws the birds eye view plan of the container
 */
function drawPlan() {
  var plan = document.getElementById("plan");
  var contextP = plan.getContext("2d");
  contextP.clearRect(0, 0, plan.width, plan.height);

  contextP.fillStyle = "#d2cbcd";
  contextP.fillRect(0, 0, plan.width, plan.height);

  //window variables

  var insulationType = $("#OpaqueConstrutionRmenu").val();
  var insulationDepth = $("#planSldOpaqueThickness").val();

  var widthFactor = ($("#windowSldOut").val() / 60.75) * WINDOW_MAX_WIDTH;

  contextP.setLineDash([0, 0]);

  if (insulationType === "3") {
    contextP.fillStyle = "#e8e5e4";
    contextP.fillRect(0, 0, plan.width, 95);
    contextP.fillStyle = "#d2cbcd";
    contextP.fillRect(
      1 + 1 * insulationDepth,
      1 + 1 * insulationDepth,
      236 - 2 * insulationDepth,
      94 - 2 * insulationDepth
    );
    contextP.fillRect(166, 95 - insulationDepth, 36, insulationDepth);
  } else if (insulationType === "3") {
    contextP.fillStyle = "#fec7d4";
    contextP.fillRect(0, 0, plan.width, 95);
    contextP.fillStyle = "#d2cbcd";
    contextP.fillRect(
      1 + 1 * insulationDepth,
      1 + 1 * insulationDepth,
      236 - 2 * insulationDepth,
      94 - 2 * insulationDepth
    );
    contextP.fillRect(166, 95 - insulationDepth, 36, insulationDepth);
  } else if (insulationType === "6") {
    contextP.fillStyle = "#fdfaaa";
    contextP.fillRect(0, 0, plan.width, 95);
    contextP.fillStyle = "#d2cbcd";
    contextP.fillRect(
      1 + 1 * insulationDepth,
      1 + 1 * insulationDepth,
      236 - 2 * insulationDepth,
      94 - 2 * insulationDepth
    );
    contextP.fillRect(166, 95 - insulationDepth, 36, insulationDepth);
  }

  //Outside And Inside Wall
  contextP.lineWidth = 2;
  contextP.strokeStyle = "#3104fb";
  contextP.beginPath();
  contextP.strokeRect(1, 1, 236, 94);
  contextP.closePath();
  contextP.beginPath();
  contextP.strokeRect(
    1 + 1 * insulationDepth,
    1 + 1 * insulationDepth,
    236 - 2 * insulationDepth,
    94 - 2 * insulationDepth
  );
  contextP.closePath();

  //White out outer door entry
  contextP.strokeStyle = "#d2cbcd";
  contextP.beginPath();
  contextP.moveTo(202, 95);
  contextP.lineTo(166, 95);
  contextP.stroke();

  //White out inner door entry
  contextP.beginPath();
  contextP.moveTo(202, 95 - insulationDepth);
  contextP.lineTo(166, 95 - insulationDepth);
  contextP.stroke();

  //White out outer window
  contextP.beginPath();
  contextP.moveTo(83 - widthFactor / 2, 95);
  contextP.lineTo(83 + widthFactor / 2, 95);
  contextP.stroke();

  //White out inner window
  contextP.beginPath();
  contextP.moveTo(83 - widthFactor / 2, 95 - insulationDepth);
  contextP.lineTo(83 + widthFactor / 2, 95 - insulationDepth);
  contextP.stroke();

  //Line of opened door
  contextP.strokeStyle = "#000000";
  contextP.beginPath();
  contextP.moveTo(166, 95);
  contextP.lineTo(166, 132);
  contextP.stroke();
  contextP.closePath();

  //Dash for outer door
  contextP.setLineDash([4, 1]);
  contextP.lineWidth = 1;
  contextP.beginPath();
  contextP.moveTo(166, 95);
  contextP.lineTo(202, 95);
  contextP.stroke();
  contextP.closePath();

  //Dash for inner door
  contextP.beginPath();
  contextP.moveTo(166, 95 - insulationDepth);
  contextP.lineTo(202, 95 - insulationDepth);
  contextP.stroke();
  contextP.closePath();

  //Circled dash for opening door
  contextP.beginPath();
  contextP.arc(166, 95, 36, 0, Math.PI / 2);
  contextP.stroke();
  contextP.closePath();

  //Dash for outer window
  contextP.beginPath();
  contextP.moveTo(83 - widthFactor / 2, 95);
  contextP.lineTo(83 + widthFactor / 2, 95);
  contextP.stroke();

  //Dash for inner window
  contextP.beginPath();
  contextP.moveTo(83 - widthFactor / 2, 95 - insulationDepth);
  contextP.lineTo(83 + widthFactor / 2, 95 - insulationDepth);
  contextP.stroke();

  //Fill in Window
  let windowOutputArea = $("#windowSldOut").val();
  if (windowOutputArea >= 1.5) {
    contextP.fillStyle = "#07ebf8";
    contextP.fillRect(
      83 - widthFactor / 2,
      95 - insulationDepth,
      widthFactor,
      insulationDepth
    );
  }
}

/**
 * This function draws the front side view of the plan
 */
function drawElevation() {
  var elevation = document.getElementById("elevation");
  var contextE = elevation.getContext("2d");

  contextE.clearRect(0, 0, elevation.width, elevation.height);
  contextE.fillStyle = "#a3bcfd";
  contextE.fillRect(0, 0, elevation.width, elevation.height);

  //window variables

  var lengthFactor = ($("#windowSldOut").val() / 60.75) * WINDOW_MAX_LENGTH;
  var widthFactor = ($("#windowSldOut").val() / 60.75) * WINDOW_MAX_WIDTH;

  // create the door
  contextE.strokeStyle = "#000000";
  contextE.lineWidth = 2;
  contextE.strokeRect(152, 28, 36, 80);

  //create the inner door
  contextE.lineWidth = 1;
  contextE.strokeRect(155, 31, 30, 74);

  //create doorknob
  contextE.beginPath();
  contextE.arc(180, 70, 2, 0, Math.PI * 2);
  contextE.stroke();
  contextE.closePath();

  //create window
  let windowOutputArea = $("#windowSldOut").val();
  if (windowOutputArea >= 1.5) {
    contextE.lineWidth = 2;
    contextE.strokeRect(83 - widthFactor / 2, 28, widthFactor, lengthFactor);
    if (widthFactor > 2) {
      contextE.lineWidth = 1;
      contextE.strokeRect(
        83 - widthFactor / 2 + 3,
        31,
        widthFactor - 6,
        lengthFactor - 6
      );
    }
  }
}
