package com.rocc.interlocking.application.service;

import com.rocc.interlocking.application.dto.RouteRequestCommand;
import com.rocc.interlocking.domain.model.RouteValidationResult;
import com.rocc.interlocking.infrastructure.websocket.NotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterlockingService {

    private final NotificationHandler notificationHandler;

    /**
     * simplified interlocking Logic for LRT
     */
    public RouteValidationResult validateRoute(RouteRequestCommand command) {
        // basic safety check statements, @todo implement lrt state
        // this is just hardcoded example:
        if (command.lrtNumber().startsWith("LRT-9")) {
            return RouteValidationResult.rejected("Maintenance block on route");
        }

        String routeId = command.requestedRouteId() != null
                ? command.requestedRouteId()
                : "ROUTE-" + System.currentTimeMillis();

        RouteValidationResult result = RouteValidationResult.approved(routeId);

        notificationHandler.sendRouteUpdate(command.lrtNumber(), result);

        return result;
    }

    /**
     * handle position update received from Kafka
     */
    public void handlePositionUpdate(String lrtNumber, double lat, double lon, int speed) {
        String message = String.format("LRT %s moved to (%.4f, %.4f) at %d km/h",
                lrtNumber, lat, lon, speed);

        notificationHandler.sendPositionUpdate(lrtNumber, lat, lon, speed, message);
    }
}