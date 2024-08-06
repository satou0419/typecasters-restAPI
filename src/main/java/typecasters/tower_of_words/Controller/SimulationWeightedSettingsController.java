package typecasters.tower_of_words.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import typecasters.tower_of_words.Entity.SimulationWeightedSettingsEntity;
import typecasters.tower_of_words.Service.SimulationWeightedSettingsService;
import typecasters.tower_of_words.Response.Response;
import typecasters.tower_of_words.Response.NoDataResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/simulation_weighted_settings")
public class SimulationWeightedSettingsController {

    @Autowired
    SimulationWeightedSettingsService simulationWeightedSettingsService;

    @PostMapping("/insert_new_simulation_weighted_settings")
    public ResponseEntity<Object> insertSimulationWeightedSettings(
            @RequestBody SimulationWeightedSettingsEntity simulationWeightedSettings,
            @RequestParam int simulationID,
            @RequestParam int creatorID) {
        try {
            SimulationWeightedSettingsEntity savedSettings = simulationWeightedSettingsService.insertSimulationWeightedSettings(simulationWeightedSettings, simulationID, creatorID);
            return Response.response(HttpStatus.CREATED, "Simulation Weighted Settings created successfully", savedSettings);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating Simulation Weighted Settings");
        }
    }

    @GetMapping("/view_all_simulation_weighted_settings")
    public ResponseEntity<Object> getAllSimulationWeightedSettings() {
        try {
            List<SimulationWeightedSettingsEntity> settingsList = simulationWeightedSettingsService.getAllSimulationWeightedSettings();
            return Response.response(HttpStatus.OK, "All Simulation Weighted Settings retrieved successfully", settingsList);
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Simulation Weighted Settings");
        }
    }

    @GetMapping("/view_simulation_weighted_settings_by/{simulationWeightedSettingsID}")
    public ResponseEntity<Object> getSimulationWeightedSettingsByID(@PathVariable int simulationWeightedSettingsID) {
        try {
            Optional<SimulationWeightedSettingsEntity> settings = simulationWeightedSettingsService.getSimulationWeightedSettingsByID(simulationWeightedSettingsID);
            if (settings.isPresent()) {
                return Response.response(HttpStatus.OK, "Simulation Weighted Settings retrieved successfully!", settings.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Weighted Settings not found!");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Simulation Weighted Settings");
        }
    }

    @GetMapping("/view_simulation_weighted_settings_by/")
    public ResponseEntity<Object> getSimulationWeightedSettingsByCreatorIDAndSimulationID(
            @RequestParam int creatorID,
            @RequestParam int simulationID) {
        try {
            Optional<SimulationWeightedSettingsEntity> settings = simulationWeightedSettingsService.getSimulationWeightedSettingsByCreatorIDAndSimulationID(creatorID, simulationID);
            if (settings.isPresent()) {
                return Response.response(HttpStatus.OK, "Simulation Weighted Settings retrieved successfully!", settings.get());
            } else {
                return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, "Simulation Weighted Settings not found!");
            }
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving Simulation Weighted Settings");
        }
    }

    @PatchMapping("/update_simulation_weighted_settings_by/")
    public ResponseEntity<Object> updateSimulationWeightedSettingsByCreatorIDAndSimulationID(
            @RequestBody SimulationWeightedSettingsEntity simulationWeightedSettings,
            @RequestParam int creatorID,
            @RequestParam int simulationID) {
        try {
            SimulationWeightedSettingsEntity updatedSettings = simulationWeightedSettingsService.updateSimulationWeightedSettingsByCreatorIDAndSimulationID(simulationWeightedSettings, creatorID, simulationID);
            return Response.response(HttpStatus.OK, "Simulation Weighted Settings updated successfully", updatedSettings);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return NoDataResponse.noDataResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating Simulation Weighted Settings");
        }
    }

    @DeleteMapping("/delete_by/{simulationWeightedSettingsID}")
    public ResponseEntity<Object> deleteSimulationWeightedSettings(@PathVariable int simulationWeightedSettingsID) {
        try {
            simulationWeightedSettingsService.deleteSimulationWeightedSettings(simulationWeightedSettingsID);
            return NoDataResponse.noDataResponse(HttpStatus.NO_CONTENT, "Simulation Weighted Settings deleted successfully");
        } catch (NoSuchElementException e) {
            return NoDataResponse.noDataResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return NoDataResponse.noDataResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting Simulation Weighted Settings");
        }
    }
}
