/**
 * Done By - Md Ishfaq Alam (A00450249)
 * functions contained - function (request, response, next), function (request, response)
 */

// Introduce express
var express = require("express");
var server = express();

var portForServer = 3400;

server.use(express.json());
server.use(express.urlencoded({ extended: true }));

// Special permissions
var allowCrossDomain = function (request, response, next) {
  response.header("Access-Control-Allow-Origin", "*");
  response.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
  response.header("Access-Control-Allow-Headers", "Content-Type");
  next();
};

server.use(allowCrossDomain);

// listen for events through the port
server.listen(portForServer);

//
server.get("/malam", function (request, response) {
  let paraForLocalConditions =
    "<br>Local conditions: <br><br> Heating demand is given in heating degree days. The length of a Canadian heating season is the number of days below 18 C. Coldness is the difference between a desired indoor temperature of 20 C and the average outdoor temperature on those days. <p>Humidity and especially windiness of a location also add to heating demand but are discussed elsewhere.</p><p>Warmer climates imply a cooling load: also a subject for other chapters.</p><p>Please note that to reflect the Canadian experience, this App mixes units: Celsius for temperature, for example, but inches and feet for dimensions.</p>";
  let paraForAnnualEnergyBudget =
    "<br>Annual Energy Budget:<br><br> Envelope heat loss is only the part of an energy budget. Lights, hot water appliances and electronics also consume energy. In this chapter those other loads are fixed, on the assumption that use of the building remains constant in all locations. <p> Envelope heat loss has at least two components: the effectively conductive losses that can be reduced by insulation, and losses due to ventilation and drafts. Both are proportional to heating demand. Looking at the Energy Budget graph, you will see that changing the insulation levels changes the conductive or insulation losses but not those due to air movement.</p>";
  let paraForDraftsAndVentilations =
    "<br>Drafts and Ventilation: <br><br>Realistically, a larger window would admit more drafts, especially at the lower end of the quality scale, but that effect is ignored in the insulation chapter.<br><br>The Drafts and Ventilation chapter explains how energy losses due to infiltration are controlled by membranes, sealants, joint design, and meticulous quality control. It shows how ventilation losses can be managed through heat exchange, flow controls, and careful use of operable windows and vents.";
  let paraForInsulationAndHeatLoss =
    "<br>Insulation and Heat loss:<br><br> In North America, thermal resistance is measured in R - values. The resistance of a material barrier is a product of its resistivity, in R/inch, and the inches of thickness. The actual effectiveness of insulation depends on installation and other factors, but this app gives drywall an R/Inch of 1, fiberglass and cellulose insulation an R/Inch of 3, and urethane spray foam an R/inch of 6.<br><br>In thin and poorly insulating assemblies, air films become significant. This is how painted sheet steel ends up with a nominal R of 1. When assemblies are layered, R Values can simply be totalled.";
  let paraForMaterialsAndInsulation =
    "<br>Materials and Insulation:<br><br>Heat flow is inversely related to thermal resistance. The conduction of heat through a material is given as an U value which is equal to 1/R. Add layers into a single R value before finding their U value.<br><br>Heat loss is a product of thermal demand and conductive liability. Thermal demand consolidates temperature difference and time, as in degree days. Thermal liability is a product of surface area and conductance.<br><br>The total thermal liability of an envelope is a sum of the liability of its portions. Average conductance divides the total liability by the total area. The effective R-value of an envelope is the inverse of average conductance.<br><br>Note that high R-value portions of an envelope have a smaller effect on the effective R-value than might be supposed. Conversely, low R-value portions of an envelope such as windows have a larger effect on overall heat loss than their small area may suggest.";
  let paraForEnviromentalImpacts =
    "<br>Enviromental Impact: <br><br>The enviromental impact of construction depends not only on the energy consumed in operating a building, but in the energy consumed or 'embodied' in the material through sourcing, manufacture, transport, and assembly. Additionally, toxins and other ecological and social injuries need to be accounted for. The exact calculations are complicated and debatable, but that's no reason to ignore them. They are the subject of several other chapters";

  // Gets the request of the client which are the paragraphs so will send a JSON object as response
  let dataForConcepts = {
    LocalConditions: paraForLocalConditions,
    AnnualEnergyBudget: paraForAnnualEnergyBudget,
    DraftsAndVentilations: paraForDraftsAndVentilations,
    InsulationAndHeatLoss: paraForInsulationAndHeatLoss,
    MaterialsAndInsulation: paraForMaterialsAndInsulation,
    EnviromentalImpacts: paraForEnviromentalImpacts,
  };

  //return message
  return response.status(200).send(dataForConcepts);
});
