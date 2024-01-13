package net.averak.gsync.domain.repository.exception

class MasterDataNotFoundException(type: Class<*>) : RuntimeException("Master data not found, type=${type.simpleName}.")
