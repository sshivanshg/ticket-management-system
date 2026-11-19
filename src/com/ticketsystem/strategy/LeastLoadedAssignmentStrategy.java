package com.ticketsystem.strategy;

import com.ticketsystem.model.Agent;

import java.util.List;

/**
 * Gives the new ticket to the agent with the fewest active tickets.
 * Proves Open/Closed: a brand-new rule, and TicketService is not touched at all.
 */
public class LeastLoadedAssignmentStrategy implements AssignmentStrategy {

    @Override
    public Agent assign(List<Agent> agents) {
        if (agents.isEmpty()) {
            throw new IllegalStateException("No agents available to assign the ticket");
        }

        // Pick the agent with the fewest active tickets.
        // Tie-break: strict "<" keeps the first agent in the list, so ties go to the earliest registered agent.
        Agent leastLoaded = agents.get(0);
        for (Agent agent : agents) {
            if (agent.getActiveTicketCount() < leastLoaded.getActiveTicketCount()) {
                leastLoaded = agent;
            }
        }
        return leastLoaded;
    }
}
