-- 1. Agregar columna id si no existe
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name='identifiers'
          AND column_name='id'
    ) THEN
        ALTER TABLE identifiers
        ADD COLUMN id BIGSERIAL;
    END IF;
END$$;

-- 2. Rellenar id con valores si hay NULL
UPDATE identifiers
SET id = nextval(pg_get_serial_sequence('identifiers','id'))
WHERE id IS NULL;

-- 3. Quitar cualquier PK existente
DO $$
DECLARE
    pk_name text;
BEGIN
    SELECT conname INTO pk_name
    FROM pg_constraint
    WHERE conrelid = 'identifiers'::regclass
      AND contype = 'p';
      
    IF pk_name IS NOT NULL THEN
        EXECUTE format('ALTER TABLE identifiers DROP CONSTRAINT %I', pk_name);
    END IF;
END$$;

-- 4. Crear PK nueva en id si no existe
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'pk_identifiers'
    ) THEN
        ALTER TABLE identifiers
        ADD CONSTRAINT pk_identifiers PRIMARY KEY (id);
    END IF;
END$$;

-- 5. Crear unique constraint en diio si no existe
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'uk_identifiers_diio'
    ) THEN
        ALTER TABLE identifiers
        ADD CONSTRAINT uk_identifiers_diio UNIQUE (diio);
    END IF;
END$$;