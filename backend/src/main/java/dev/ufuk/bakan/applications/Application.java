package dev.ufuk.bakan.applications;

import dev.ufuk.bakan.entities.Account;

public abstract class Application<T> {
    protected Account applicant;
    public abstract T apply();
}
